package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by teo on 4/8/17.
 */
public class BTController extends Controller {

    public Result trips(String from, String to) {
        return ok(views.html.trips.render());
    }

    public Result createTrip() {
        return ok(views.html.createTrip.render());
    }

}
