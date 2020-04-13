package epam.my.project.service;

import epam.my.project.form.SignInForm;
import epam.my.project.form.SignUpForm;
import epam.my.project.model.Principal;
import java.sql.SQLException;

public interface AuthenticateAndAuthorizationService {
     Principal signIn(SignInForm signInForm) throws SQLException;

     Principal signUpByManually(SignUpForm signInForm) throws SQLException;

     boolean isAuthorized(Principal principal, String url);
}
