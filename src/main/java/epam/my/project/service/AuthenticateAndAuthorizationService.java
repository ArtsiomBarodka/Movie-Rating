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

/**
 * The interface Authenticate and authorization service.
 */
public interface AuthenticateAndAuthorizationService {
    /**
     * Already exist account email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean alreadyExistAccountEmail(String email) throws InternalServerErrorException;

    /**
     * Already exist account name boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean alreadyExistAccountName(String name) throws InternalServerErrorException;

    /**
     * Create account auth token account auth token.
     *
     * @param accountDetails the account details
     * @return the account auth token
     * @throws InternalServerErrorException the internal server error exception
     */
    AccountAuthToken createAccountAuthToken (AccountDetails accountDetails) throws InternalServerErrorException;

    /**
     * Already exist account auth token boolean.
     *
     * @param selector the selector
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean alreadyExistAccountAuthToken(String selector) throws InternalServerErrorException;

    /**
     * Update validator string.
     *
     * @param accountAuthToken the account auth token
     * @return the string
     * @throws InternalServerErrorException the internal server error exception
     */
    String updateValidator(AccountAuthToken accountAuthToken) throws InternalServerErrorException;

    /**
     * Gets account auth token.
     *
     * @param selector  the selector
     * @param validator the validator
     * @return the account auth token
     * @throws InternalServerErrorException the internal server error exception
     * @throws AccessDeniedException        the access denied exception
     */
    AccountAuthToken getAccountAuthToken (String selector, String validator) throws InternalServerErrorException, AccessDeniedException;

    /**
     * Sign in by is remember me account details.
     *
     * @param accountAuthToken the account auth token
     * @return the account details
     * @throws InternalServerErrorException the internal server error exception
     * @throws AccessDeniedException        the access denied exception
     */
    AccountDetails signInByIsRememberMe(AccountAuthToken accountAuthToken) throws InternalServerErrorException, AccessDeniedException;

    /**
     * Sign in by manually account details.
     *
     * @param signInForm the sign in form
     * @return the account details
     * @throws InternalServerErrorException the internal server error exception
     * @throws AccessDeniedException        the access denied exception
     * @throws ValidationException          the validation exception
     */
    AccountDetails signInByManually(SignInForm signInForm) throws InternalServerErrorException, AccessDeniedException, ValidationException;

    /**
     * Sign in by social account details.
     *
     * @param socialAccount the social account
     * @return the account details
     * @throws InternalServerErrorException the internal server error exception
     * @throws AccessDeniedException        the access denied exception
     */
    AccountDetails signInBySocial(SocialAccount socialAccount) throws InternalServerErrorException, AccessDeniedException;

    /**
     * Sign up by manually account details.
     *
     * @param signInForm the sign in form
     * @return the account details
     * @throws ValidationException          the validation exception
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    AccountDetails signUpByManually(SignUpForm signInForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException;

    /**
     * Sign up by social account details.
     *
     * @param socialAccount        the social account
     * @param signUpWithSocialForm the sign up with social form
     * @return the account details
     * @throws ValidationException          the validation exception
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    AccountDetails signUpBySocial(SocialAccount socialAccount, SignUpWithSocialForm signUpWithSocialForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException;

    /**
     * Is secured url boolean.
     *
     * @param url the url
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean isSecuredUrl(String url) throws InternalServerErrorException;

    /**
     * Is authorized boolean.
     *
     * @param accountDetails the account details
     * @param url            the url
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     * @throws AccessDeniedException        the access denied exception
     */
    boolean isAuthorized(AccountDetails accountDetails, String url) throws InternalServerErrorException, AccessDeniedException;
}
