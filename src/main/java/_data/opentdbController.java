package _data;

import _helpers.QuizResponse;
import _models.question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@RestController
public class opentdbController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/opentdb")
    public String getQuestions(){
        String url ="https://opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=multiple";
        String jsonRes = restTemplate.getForObject(url,String.class);
        System.out.println(jsonRes);
        return jsonRes;
    }

    @GetMapping("/opentdb/{count}")
    public String getQuestions(@PathVariable("count") String count, String cat, String dif, String type) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://opentdb.com/api.php?"
                +"amount="+count
                +"&category="+cat
                +"&difficulty="+dif
                +"&type="+type
        ;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    /*
    @GetMapping("/qlist")
    public List<question> getQuestionsList() throws JsonProcessingException {
        //Task
        //Make external API call to OpenTDB
        //Get JSON response
        //Parse JSON response and read JSON List
        String url ="https://opentdb.com/api.php?amount="+10 +"&category=9&difficulty=easy&type=multiple";
        String jsonRes = restTemplate.getForObject(url,String.class);
        System.out.println(jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        QuizResponse quizResponse = null;

        try {
            quizResponse = mapper.readValue(jsonRes, QuizResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(quizResponse.getResults().size());
        //return  JSON List

        return quizResponse.getResults();
    }


    @GetMapping("/qlist2")
    public List<question> getQuestionsList2() throws JsonProcessingException {
        //Task
        //Make external API call to OpenTDB
        //Get JSON response
        //Parse JSON response and read JSON List
        String url ="https://opentdb.com/api.php?amount="+10 +"&category=9&difficulty=easy&type=multiple";
        String jsonRes = restTemplate.getForObject(url,String.class);

        String questions = jsonRes.substring(29, jsonRes.length()-1);
        System.out.println(jsonRes);
        System.out.println(questions);
        ObjectMapper mapper = new ObjectMapper();

        List<question> questionsList = null;
        try {
            questionsList = mapper.readValue(questions,new TypeReference<List<question>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(quizResponse.getResults().size());
        //return  JSON List

        return questionsList;
    }

    */
}
