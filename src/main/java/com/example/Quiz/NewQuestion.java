package com.example.Quiz;

import _data.dbController;
import _data.opentdbController;
import _helpers.opentdbQuestion;
import _helpers.rootOptResponce;
import _models.question;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "NewQuestion", value = "/NewQuestion")
public class NewQuestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        opentdbController odb = new opentdbController();
        dbController db = new dbController();

        // Info about the Tournament
        String tournId = request.getParameter("tournId");
        request.setAttribute("tournId", tournId);

        String existQuestions = "";
        try {
            ResultSet questionRes = db.getQuestions(tournId);
            existQuestions = existQuestions + "<ul>";
            while (questionRes.next()) {
                existQuestions = existQuestions +
                    "<li>" +
                    // questionRes.getInt("id") + ". " +
                    questionRes.getString("question") + " (" +
                    questionRes.getString("difficulty") + ") " +
                    "</li>";
            }
            existQuestions = existQuestions + "</ul>";

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("existQuestions", existQuestions);



        // =====================================
        // List of the new questions

        // default parameters
        String category = "9";
        String amount = "5";
        String difficulty = "easy";
        String type = "multiple";

        // parameters from the form

        if( request.getParameter("refresh")!=null) {
            if (request.getParameter("category")!=null ) {
                category = request.getParameter("category");
            }
            if (request.getParameter("amount")!=null ) {
                amount = request.getParameter("amount");
            }
            if (request.getParameter("difficulty")!=null ) {
                difficulty = request.getParameter("difficulty");
            }
            if (request.getParameter("type")!=null ) {
                type = request.getParameter("type");
            }
        }

        String jQList = odb.getQuestions(amount, category, difficulty, type);

        String encodedQList = Base64.getUrlEncoder().encodeToString(jQList.getBytes());
        request.setAttribute("encodedQList", encodedQList);


        ObjectMapper om = new ObjectMapper();
        rootOptResponce optResponce = om.readValue(jQList, rootOptResponce.class);
        List<opentdbQuestion> listOpentdb =  optResponce.results;
        request.setAttribute("listOpentdb", listOpentdb);

        // -------------------------------------


        // web page
        request.getRequestDispatcher("newQuestion.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // adding new questions
        if( Objects.equals(request.getParameter("add"), "true")) {

            String questionSet =  request.getParameter("questionSet");
            byte[] decodedBytes = Base64.getUrlDecoder().decode(questionSet);
            questionSet = new String(decodedBytes);

            ObjectMapper om = new ObjectMapper();

            rootOptResponce optResponce = om.readValue(questionSet, rootOptResponce.class);


            if (optResponce.results.size() > 0 ) {
                dbController db = new dbController();
                for (int i = 0; i < optResponce.results.size(); i++) {

                    System.out.println(optResponce.results.get(i));

                    Integer questId = 0;
                    try {
                        questId = db.setQuestion(
                            request.getParameter("tournId"),
                            optResponce.results.get(i).question,
                            optResponce.results.get(i).category,
                            optResponce.results.get(i).type,
                            optResponce.results.get(i).difficulty,
                            true
                        );
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }


                    // add answers
                    try {
                        if (questId>0 ) {
                            Integer answId = db.setAnswer(
                                questId.toString(),
                                optResponce.results.get(i).correct_answer,
                                "1",
                                true
                            );

                            List<String> incorAnsw = optResponce.results.get(i).getIncorrect_answers();
                            if (incorAnsw.size()>0) {
                                for (int j = 0; j < incorAnsw.size(); j++) {
                                    answId = db.setAnswer(
                                        questId.toString(),
                                        incorAnsw.get(j),
                                        "0",
                                        true
                                    );
                                }
                            }
                        }

                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }


        }

        this.doGet(request, response);
    }
}
