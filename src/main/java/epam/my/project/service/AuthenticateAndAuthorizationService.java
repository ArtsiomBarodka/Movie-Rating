package epam.my.project.service;

import epam.my.project.form.SignInForm;
import epam.my.project.form.SignUpForm;
import epam.my.project.model.AccountDetails;
import epam.my.project.model.SocialAccount;

import java.io.IOException;
import java.sql.SQLException;

public interface AuthenticateAndAuthorizationService {
     AccountDetails signInByManually(SignInForm signInForm) throws SQLException;

     AccountDetails signInBySocial(SocialAccount socialAccount) throws SQLException;

     AccountDetails signUpByManually(SignUpForm signInForm) throws SQLException, IOException;

     AccountDetails SignUpBySocial(SocialAccount socialAccount, String accountName) throws SQLException, IOException;

     boolean isAuthorized(AccountDetails accountDetails, String url);
}
