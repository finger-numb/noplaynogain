package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by penic on 08.04.17..
 */
@Entity
public class BTLocation extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @Column(unique=true)
    public String name;

    public static Finder<Long, BTLocation> finder = new Model.Finder<>(BTLocation.class);

    public static List<BTLocation> findAll(){
        return finder.all();
    }

}
