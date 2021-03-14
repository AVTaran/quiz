package _helpers;

import _models.question;

import java.util.List;

public class QuizResponse {

    int response_code;
    List<question> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<question> getResults() {
        return results;
    }

    public void setResults(List<question> results) {
        this.results = results;
    }

}
