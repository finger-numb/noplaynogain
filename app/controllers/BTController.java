package controllers;

import models.BTLocation;
import models.BTTrip;
import models.BTUser;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return ok(createTrip.render());
    }

    public Result saveTrip() {
        DynamicForm form = Form.form().bindFromRequest();
        List<BTLocation> locations = new ArrayList<>();
        Map<String, String> data = form.data();
        BTTrip trip = parseTrip(data);


        BTUser currentUser = Users.currentUser();
        currentUser.trips.add(trip);
        currentUser.save();

        return redirect(routes.BTController.createTrip());
    }

    private static BTTrip parseTrip(Map<String, String> data){
        BTTrip trip = new BTTrip();

        trip.owner = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            trip.startDate = df.parse(data.get("date").replace('T', ' '));
        } catch(ParseException e){
            // TODO
            e.printStackTrace();
        }
        trip.numOfPlaces = Integer.parseInt(data.get("numOfPlaces"));
        trip.price = Integer.parseInt(data.get("price"));

        for(String key : data.keySet()){
            if(key.startsWith("locations")){
                BTLocation location = new BTLocation(data.get(key));
                location.save();
                trip.locations.add(location);
            }
        }

        return trip;
    }

}
