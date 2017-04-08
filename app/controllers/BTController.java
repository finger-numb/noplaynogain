package controllers;

import models.BTLocation;
import models.BTTrip;
import models.BTUser;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.*;

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
        List<BTLocation> locations = BTLocation.findAll();
        return ok(createTrip.render(locations));
    }

    public Result saveTrip() {
        DynamicForm form = Form.form().bindFromRequest();
        List<BTLocation> locations = new ArrayList<>();
        int index = 0;
//        BTTrip trip = new BTTrip(Users.currentUser(),
//                null,
//                Integer.parseInt(form.get("numOfPlaces")),
//                Integer.parseInt(form.get("price")),
//                null,
//                null
//                );
        return ok(form.get("locations[]").toString());
    }


}
