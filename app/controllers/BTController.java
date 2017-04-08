package controllers;

import models.BTLocation;
import models.BTTrip;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teo on 4/8/17.
 */
public class BTController extends Controller {

    public Result trips(String from, String to) {
        boolean fromAll = from.isEmpty();
        boolean toAll = to.isEmpty();
        List<BTTrip> allTrips = BTTrip.findAll();
        List<BTTrip> filteredTrips = new ArrayList<>();
        for (BTTrip trip : allTrips) {
            List<BTLocation> locations = trip.locations;
                boolean fromTrue = false;
            for (BTLocation location : locations) {
                if (location.name.equals(from) || fromAll) {
                    fromTrue = true;
                }
                if (fromTrue && location.name.equals(to) || toAll && fromTrue) {
                    filteredTrips.add(trip);
                    break;
                }
            }
        }
        return ok(views.html.trips.render(filteredTrips));
    }

    public Result createTrip() {
        return ok(views.html.createTrip.render());
    }

    public Result saveTrip() {
        DynamicForm form = Form.form().bindFromRequest();
        return ok(form.get("date"));
    }


}
