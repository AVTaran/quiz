package _data;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dbController {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quiz";
    private String username = "root";
    private String password = "";

    Connection connection;

    public static void main(String[] args) throws IOException, SQLException {

    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(this.jdbcURL, this.username, this.password);
    }

    public ResultSet getUsers() throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectUsers = "SELECT * FROM `users`;";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectUsers);
        return res;
    }

    public ResultSet getTournaments() throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectTournaments = "SELECT * FROM `tournaments`;";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectTournaments);
        return res;
    }

    public ResultSet getQuestions(String tournId) throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectQuestions = "SELECT * FROM `questions`";
        if (Integer.parseInt(tournId)>0) {
            sqlSelectQuestions = sqlSelectQuestions + " WHERE `id_tournament` = '" + tournId + "'";
        }
        sqlSelectQuestions = sqlSelectQuestions + ";";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectQuestions);
        return res;
    }

    public ResultSet getAnswers(String questId) throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectAnswers = "SELECT * FROM `answers`";
        if (Integer.parseInt(questId)>0) {
            sqlSelectAnswers = sqlSelectAnswers + " WHERE `id_tournament` = '" + questId + "'";
        }
        sqlSelectAnswers = sqlSelectAnswers +";";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectAnswers);
        return res;
    }

    public Boolean setTournament(String name, LocalDateTime DateStart, LocalDateTime DateStop) throws SQLException, ClassNotFoundException, ParseException {
        this.connect();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 2021-03-16 00:00:00
        String sqlAddTournament =
                "INSERT INTO `tournaments` (`name`, `dataStart`, `dataStop`) " +
                "VALUES ('"+name+"', '"+
                        DateStart.format(formatter)+ "', '"+
                        DateStop.format(formatter) + "'); ";
        Statement statement = this.connection.createStatement();
        Boolean res = statement.execute(sqlAddTournament);
        return res;
    }

    public Integer setQuestion(String tournId, String question, String category, String type, String diff, Boolean retId) throws SQLException, ClassNotFoundException {
        this.connect();
        Integer ret = 0;

        // adding new question
        String sqlAddQuestion =
            "INSERT INTO `questions` (`id_tournament`, `question`, `category`, `type`, `difficulty`) " +
                "VALUES (" +
                    "'"+tournId+"', " +
                    "'"+question+"', " +
                    "'"+category+"', " +
                    "'"+type+"', " +
                    "'"+diff+"' " +
                "); "
            ;
        Statement statement = this.connection.createStatement();
        Boolean resAddQuest  = statement.execute(sqlAddQuestion);

        // if need to return the id
        if (resAddQuest && retId) {
            statement = this.connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT LAST_INSERT_ID();");
            ret = Integer.parseInt(res.getString(1));
        }
        return ret;
    }


    public Integer setAnswer (String questId, String answer, String correct, Boolean retId) throws SQLException, ClassNotFoundException {
        this.connect();
        Integer ret = 0;

        // adding new answer
        String sqlAddAnswer =
            "INSERT INTO `answers` (`id_question`, `answer`, `correct`) " +
                "VALUES (" +
                "'"+questId+"', " +
                "'"+answer+"', " +
                "'"+correct+"' " +
                "); "
            ;
        Statement statement = this.connection.createStatement();
        Boolean resAddAnswer  = statement.execute(sqlAddAnswer);

        // if need to return the id
        if (resAddAnswer && retId) {
            statement = this.connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT LAST_INSERT_ID();");
            ret = Integer.parseInt(res.getString(1));
        }
        return ret;
    }



}
