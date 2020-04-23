package epam.my.project.service;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.form.SignInForm;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;

public interface AuthenticateAndAuthorizationService {

     boolean alreadyExistAccount(String email) throws InternalServerErrorException;

     AccountDetails signInByManually(SignInForm signInForm) throws InternalServerErrorException, AccessDeniedException;

     AccountDetails signInBySocial(SocialAccount socialAccount) throws InternalServerErrorException, AccessDeniedException;

     AccountDetails signUpByManually(SignUpForm signInForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException;

     AccountDetails SignUpBySocial(SocialAccount socialAccount, String accountName) throws  ValidationException, InternalServerErrorException;

     boolean isSecuredUrl(String url) throws InternalServerErrorException;

     boolean isAuthorized(AccountDetails accountDetails, String url) throws InternalServerErrorException, AccessDeniedException;
}
