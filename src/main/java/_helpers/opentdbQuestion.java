package _helpers;

import java.util.List;

public class opentdbQuestion {
    /*
    public String category;
    public String type;
    public String difficulty;
    public String question;
    public String correct_answer;
    public List<String> incorrect_answers;
    */

    public String id;
    public String question;
    public String quizID;

    public String category;
    public String type;
    public String difficulty;

    public String correct_answer;
    public List<String> incorrect_answers;


    public opentdbQuestion(){

    }
    public opentdbQuestion(String id, String question, String quizID, String category, String type, String difficulty, String correct_answer, List<String> incorrect_answers) {
        this.id = id;
        this.question = question;
        this.quizID = quizID;
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(List<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }


}
