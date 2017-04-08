package models;

import javax.persistence.*;

/**
 * Created by penic on 08.04.17..
 */
@Entity
public class BTLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @Column(unique=true)
    public String name;

}
