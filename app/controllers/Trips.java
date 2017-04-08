package controllers;

import models.BTLocation;
import models.BTTrip;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by penic on 08.04.17..
 */
public class Trips extends Controller {

    public Result listAllTrips(){
        List<BTTrip> trips = BTTrip.findAll();
        return null;
    }

    public Result listAllLocations(){
        List<BTLocation> locations = BTLocation.findAll();
        return null;
    }

}
