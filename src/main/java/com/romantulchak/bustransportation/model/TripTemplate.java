package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TripTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private User user;


}
