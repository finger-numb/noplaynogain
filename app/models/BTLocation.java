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

    public String name;

    public BTLocation(String name){
        this.name = name;
    }

}
