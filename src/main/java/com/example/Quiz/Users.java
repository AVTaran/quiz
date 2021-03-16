package com.example.Quiz;

import _data.dbController;
// import jdk.internal.icu.impl.CharacterIteratorWrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {
    private String message;

    public void init() {
        message = "There are users:";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbController db = new dbController();
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        try {
            ResultSet usersRes = db.getUsers();

            out.println("<ul>");
            while (usersRes.next()) {
                out.println("<li>" +
                        usersRes.getInt("id") + ". " +
                        usersRes.getString("name") + " (" +
                        usersRes.getString("address") + ") " +
                        "</li>"
                );
            }
            out.println("</ul>");
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }


        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
