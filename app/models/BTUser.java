package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import play.libs.F;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by penic on 08.04.17..
 */
@Entity
public class BTUser extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @Constraints.Required
    @Column(unique=true)
    @Constraints.ValidateWith(value= BTUser.UsernameValidator.class, message = "Username must contain " +
            "at least 3 characters. All letters, numbers, points, dashes and underscores allowed.")
    public String username;

    @Constraints.Required
    public String password;

    public String firstName;
    public String lastName;
    public Image profilePicture;

    @Column(unique = true)
    @Constraints.Email
    public String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    public List<BTTrip> trips = new ArrayList<>();

    @Override
    public String toString() {
        return "Username=" + username + ", firstName=" + firstName + ", lastName=" + lastName;
    }

    public static Finder<Long, BTUser> finder = new Finder<>(BTUser.class);

    public static List<BTUser> findAll() {
        return finder.all();
    }

    public static BTUser checkUser(String username, String password) {
        return finder.where().eq("username", username).and().eq("password", password).findUnique();
    }

    public static BTUser findUserByUsername(String username) {
        return finder.where().eq("username", username).findUnique();
    }

    public static BTUser findUserById(Long id) {
        return finder.where().eq("id", id).findUnique();
    }

    public static class UsernameValidator extends Constraints.Validator<String> {
        @Override
        public boolean isValid(String username) {
            String pattern="^[a-zA-Z0-9._-]{3,}$";
            return username != null && username.matches(pattern);
        }

        @Override
        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return new F.Tuple<>("error.invalid.username", new Object[]{});
        }
    }

}
