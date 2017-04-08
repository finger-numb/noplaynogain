package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by penic on 08.04.17..
 */
@Entity
public class BTTrip extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    public BTUser owner;

    public Date startDate;

    public Integer numOfPlaces;

    public Integer price;

    @ManyToMany
    public List<BTLocation> locations = new ArrayList<>();

    @ManyToMany
    public List<BTUser> passengers = new ArrayList<>();

    public static Finder<Long, BTTrip> finder = new Finder<>(BTTrip.class);

    public static List<BTTrip> findAll(){
        return finder.all();
    }

}
