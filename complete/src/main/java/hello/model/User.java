package hello.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Deprecated
@Entity
//@NamedQuery(name = "User.findByEmailAddress",query = "select u from User u where u.emailAddress = ?1")
public class User {

    @Id
    @GeneratedValue
    Long id;
    String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}