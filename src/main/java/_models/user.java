package _models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
public class user {
    @Id
    Integer id;
    String name;
    String address;


}
