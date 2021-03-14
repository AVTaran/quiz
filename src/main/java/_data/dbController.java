package _data;

import _models.user;

import java.io.IOException;
import java.sql.*;

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
        String sqlSelectUsers = "SELECT * FROM `tournaments`;";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectUsers);
        return res;
    }

    public ResultSet getQuestions() throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectUsers = "SELECT * FROM `questions`;";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectUsers);
        return res;
    }

    public ResultSet getAnswers() throws SQLException, ClassNotFoundException {
        this.connect();
        String sqlSelectUsers = "SELECT * FROM `answers`;";
        Statement statement = this.connection.createStatement();
        ResultSet res = statement.executeQuery(sqlSelectUsers);
        return res;
    }

}
