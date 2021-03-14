package _models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;


@Entity(name="tournament")
public class tournament {
    @Id
    Integer id;
    String name;
    Date dataStart;
    Date dataStop;

}
