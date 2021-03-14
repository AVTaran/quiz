package _models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="question")
public class question {
    @Id
    Integer id;
    Integer id_tournament;
    String question;

    String category;
    String type;
    String difficulty;

}
