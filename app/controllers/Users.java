package controllers;

import models.BTUser;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by penic on on 08.04.17..
 */
@Security.Authenticated(Secured.class)
public class Users extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result logout() {
        session().clear();
        return redirect(routes.Public.loginForm());
    }

    public Result profile() {
        BTUser user = currentUser();
        return ok(profile.render(user));
    }

    public static BTUser currentUser() {
        return BTUser.findUserByUsername(ctx().session().get("username"));
    }

}
