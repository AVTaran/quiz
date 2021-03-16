<%@ page import="_helpers.opentdbQuestion" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: AutoRun
  Date: 3/15/2021
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New question</title>
    <style>
        .clearfix {
            overflow: auto;
        }
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }
    </style>
</head>
<body>
    <h3>Tournament: # ${tournId}</h3>
    <h4>List of existing questions</h4>
    ${existQuestions}
    <br/>
    <hr>

    <h4>Add new questions:</h4>
    <form action="/NewQuestion" method="get">
        <div class="clearfix">
            <div style="float: left;">
                <p>Number of Questions:</p>
                <input name="amount" value="5">
            </div>
            <div style="float: left;">
                <p>Category:</p>
                <input name="category" value="any">
            </div>
            <div style="float: left;">
                <p>Difficulty:</p>
                <input name="difficulty" value="any">
            </div>
            <div style="float: left;">
                <p>Type:</p>
                <input name="type" value="multiple">
            </div>
            <input type="hidden" name="tournId" value="${tournId}">
            <input type="hidden" name="refresh" value="true">
        </div>
        <div class="clearfix">
            <button type="submit">Refresh</button>
        </div>
    </form>
    <br/>
    <hr>

    <form action="/NewQuestion" method="post">
       <ul>
           <%
               ArrayList<opentdbQuestion> list = (ArrayList<opentdbQuestion>) request.getAttribute("listOpentdb");
               for(opentdbQuestion question : list) {
                   out.println("<li stile='margin-bottom:20px;'>");
                   out.println(question.getQuestion());

                   out.println("<ol>");
                   out.println("<li><b>" + question.getCorrect_answer() + "</b></li>");
                   ArrayList<String> incorrectAnsw = (ArrayList<String>) question.getIncorrect_answers();
                   for(String answ : incorrectAnsw) {
                       out.println("<li>" + answ + "</li>");
                   }
                   out.println("</ol>");
                   out.println("</li>");
               }
           %>
        </ul>
        <input name="questionSet" type="hidden" value='${encodedQList}'>
        <input type="hidden" name="tournId" value="${tournId}">
        <button type="submit" name="add" value="true">Add</button>
    </form>
</body>
</html>
