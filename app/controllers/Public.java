package controllers;

import models.BTUser;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;

/**
 * Created by penic on on 08.04.17..
 */
public class Public extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result loginForm() {
        return ok(login.render(formFactory.form(BTUser.class)));
    }

    public Result landing() {
        return ok(landing.render());
    }

    public Result register() {
        Form<BTUser> boundForm = formFactory.form(BTUser.class).bindFromRequest();

        if (boundForm == null) {
            return null;
        }

        if(boundForm.hasErrors()){
            flash("error", "Incorrect register!");
            return badRequest(register.render(boundForm));
        }

        BTUser user = boundForm.get();

        if(user.id == null) {
            user.save();
        } else {
            user.update();
        }

        flash("success", String.format("Hello %s! Please login to your new account.", user));

        return redirect(routes.Public.loginForm());
    }

    public Result notAuthLoginForm() {
        flash("error", "Login first to access the requested page!");
        return redirect(routes.Public.loginForm());
    }

    public Result login() {
        Form<BTUser> boundForm = formFactory.form(BTUser.class).bindFromRequest();

        BTUser enteredUser;
        try {
            enteredUser = boundForm.get();
        } catch (IllegalStateException ex) {
            return loginError(boundForm);
        }

        BTUser user = BTUser.checkUser(enteredUser.username, enteredUser.password);
        if (user == null) {
            return loginError(boundForm);
        }

        session().clear();
        session("username", user.username);

        return redirect(routes.Public.landing());
    }

    private Result loginError(Form<BTUser> boundForm) {
        flash("error", "Incorrect login!");
        return badRequest(views.html.login.render(boundForm));
    }

    public Result registerForm() {
        return ok(register.render(formFactory.form(BTUser.class).bindFromRequest()));
    }


}
