package epam.my.project.service;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignInForm;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.form.SignUpWithSocialForm;

public interface AuthenticateAndAuthorizationService {
     boolean alreadyExistAccountEmail(String email) throws InternalServerErrorException;

     boolean alreadyExistAccountName(String name) throws InternalServerErrorException;

     AccountAuthToken createAccountAuthToken (AccountDetails accountDetails) throws InternalServerErrorException;

     boolean alreadyExistAccountAuthToken(String selector) throws InternalServerErrorException;

     String updateValidator(AccountAuthToken accountAuthToken) throws InternalServerErrorException;

     AccountAuthToken getAccountAuthToken (String selector, String validator) throws InternalServerErrorException, AccessDeniedException;

     AccountDetails signInByIsRememberMe(AccountAuthToken accountAuthToken) throws InternalServerErrorException, AccessDeniedException;

     AccountDetails signInByManually(SignInForm signInForm) throws InternalServerErrorException, AccessDeniedException, ValidationException;

     AccountDetails signInBySocial(SocialAccount socialAccount) throws InternalServerErrorException, AccessDeniedException;

     AccountDetails signUpByManually(SignUpForm signInForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException;

     AccountDetails SignUpBySocial(SocialAccount socialAccount, SignUpWithSocialForm signUpWithSocialForm) throws  ValidationException, InternalServerErrorException;

     boolean isSecuredUrl(String url) throws InternalServerErrorException;

     boolean isAuthorized(AccountDetails accountDetails, String url) throws InternalServerErrorException, AccessDeniedException;
}
