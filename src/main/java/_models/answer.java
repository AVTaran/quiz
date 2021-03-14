package _models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="answer")
public class answer {
    @Id
    Integer id;
    Integer id_question;
    String answer;
    boolean correct;
}
