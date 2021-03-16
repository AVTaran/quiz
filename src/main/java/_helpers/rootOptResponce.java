package _helpers;

import java.util.List;

public class rootOptResponce {
    public int response_code;
    public List<opentdbQuestion> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<opentdbQuestion> getResults() {
        return results;
    }

    public void setResults(List<opentdbQuestion> results) {
        this.results = results;
    }



}
