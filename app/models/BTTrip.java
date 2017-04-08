package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Hari on 8.4.2017..
 */

@Entity
public class BTTrip extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    private String startingPoint;

    private String endPoint;

    private String details;

    private Date tripDate;

    private Double price;

    private Integer maxNumOfSeats;

    private Integer availableNumOfSeats;

    @OneToMany
    private List<BTUser> registratedPassengers;



}
