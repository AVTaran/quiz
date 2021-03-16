package com.example.Quiz;

import _data.dbController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

@WebServlet(name = "Tournaments", value = "/Tournaments")
public class Tournaments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ( request.getParameter("new") != null && request.getParameter("new").equals("1") ) {

            request.getRequestDispatcher("newTournament.jsp").forward(request, response);

        } else {
            dbController db = new dbController();
            String newTournament = "";

            if ( request.getParameter("name") != null
              && request.getParameter("dataStart") != null
              && request.getParameter("dataStop") != null
            ) {
                try {
                    db.setTournament(
                            request.getParameter("name"),
                            LocalDateTime.parse(request.getParameter("dataStart")),
                            LocalDateTime.parse(request.getParameter("dataStop"))
                    );
                    newTournament = "New tournament has been added";
                } catch (SQLException | ClassNotFoundException | ParseException throwables) {
                    throwables.printStackTrace();
                }
            }


            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println(newTournament);
            out.println("<h1>Tournaments:</h1>");

            try {
                ResultSet usersRes = db.getTournaments();

                out.println("<ul>");
                while (usersRes.next()) {
                    out.println("<li>" +
                            usersRes.getInt("id") + ". " +
                            usersRes.getString("name") + " (" +
                            usersRes.getDate("dataStart") + " ~ " +
                            usersRes.getDate("dataStop") + ") " +
                            "<a href='NewQuestion?tournId="+usersRes.getInt("id")+"'>Add new questions</a>" +
                            "</li>"
                    );
                }
                out.println("</ul>");
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
            out.println("<a href='Tournaments?new=1'>New tournament</a>");
            out.println("</body></html>");
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
