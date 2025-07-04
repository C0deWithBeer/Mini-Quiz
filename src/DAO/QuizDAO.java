package DAO;

import Models.Question;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuizDAO {

    public List<Question> getAllquestions() throws SQLException {

        List<Question> questionList = new ArrayList<>();
        String query = "SELECT * FROM questions";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question question = new Question();
                question.setQuestion(resultSet.getString("question"));
                question.setOptionA(resultSet.getString("optiona"));
                question.setOptionB(resultSet.getString("optionb"));
                question.setOptionC(resultSet.getString("optionc"));
                question.setOptionD(resultSet.getString("optiond"));
                question.setAnswer(resultSet.getString("answer"));
                questionList.add(question);
            }
        }
        return questionList;
    }

    public void insertQuestion(Question question) throws SQLException {

        String query = "INSERT INTO miniquiz_db.questions (question, optiona, optionb, optionc, optiond, answer) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        statement.setString(1, question.getQuestion());
        statement.setString(2, question.getOptionA());
        statement.setString(3, question.getOptionB());
        statement.setString(4, question.getOptionC());
        statement.setString(5, question.getOptionD());
        statement.setString(6, question.getAnswer());
        int result = statement.executeUpdate();

        if (result != 0){
            System.out.print("\n\nQuestion Added!");
        }

        statement.close();
    }



    public void updateQuestion(Question question, int id) throws SQLException {

        String query = "UPDATE questions SET question = ?, optiona = ?, optionb = ?, optionc = ?, optiond = ?, answer = ? WHERE id = ?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);

        statement.setString(1, question.getQuestion());
        statement.setString(2, question.getOptionA());
        statement.setString(3, question.getOptionB());
        statement.setString(4, question.getOptionC());
        statement.setString(5, question.getOptionD());
        statement.setString(6, question.getAnswer());
        statement.setInt(7, id);

        int result = statement.executeUpdate();

        if (result != 0){
            System.out.println("\nQuestion updated!");
        }

        statement.close();
    }
}
