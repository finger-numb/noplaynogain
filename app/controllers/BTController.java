package controllers;

import models.BTLocation;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.List;

/**
 * Created by teo on 4/8/17.
 */
public class BTController extends Controller {

    public Result trips(String from, String to) {
        return ok(views.html.trips.render());
    }

    public Result createTrip() {
        List<BTLocation> locations = BTLocation.findAll();
        return ok(createTrip.render(locations));
    }

}
