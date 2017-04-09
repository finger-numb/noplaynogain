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

    public BTLocation(String name){
        this.name = name;
    }

    public static Finder<Long, BTLocation> finder = new Model.Finder<>(BTLocation.class);

    public static List<BTLocation> findAll(){
        return finder.all();
    }

    public static BTLocation findByName(String name){
        return finder.where().eq("name", name).findUnique();
    }

}
