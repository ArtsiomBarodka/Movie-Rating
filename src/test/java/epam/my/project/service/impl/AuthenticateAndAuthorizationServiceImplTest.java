package epam.my.project.service.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.dao.AccountAuthTokenDAO;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.RoleDAO;
import epam.my.project.exception.*;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.entity.Account;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.entity.Role;
import epam.my.project.model.form.SignInForm;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.model.form.SignUpWithSocialForm;
import epam.my.project.model.validation.Violations;
import epam.my.project.util.DataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AccountDAO.class, AccountAuthTokenDAO.class, AccountAuthTokenDAO.class,
        RoleDAO.class, Account.class, AccountAuthToken.class, DataUtil.class, SecurityConfiguration.class})
public class AuthenticateAndAuthorizationServiceImplTest {
    private AuthenticateAndAuthorizationServiceImpl authenticateAndAuthorizationServiceImpl;
    private AccountDAO accountDAO;
    private AccountAuthTokenDAO accountAuthTokenDAO;
    private RoleDAO roleDAO;
    private Account account;
    private AccountAuthToken accountAuthToken;

    @Before
    public void init(){
        accountAuthToken = mock(AccountAuthToken.class);
        account = mock(Account.class);
        accountDAO = mock(AccountDAO.class);
        accountAuthTokenDAO = mock(AccountAuthTokenDAO.class);
        roleDAO = mock(RoleDAO.class);
        authenticateAndAuthorizationServiceImpl = new AuthenticateAndAuthorizationServiceImpl(accountDAO, accountAuthTokenDAO, roleDAO);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountEmail_NULL_EMAIL_PARAMETER() throws InternalServerErrorException {
        String email = null;
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountEmail(email);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountEmail_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        String email = "email";
        when(accountDAO.getAccountByEmail(email)).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountEmail(email);
    }

    @Test()
    public void testAlreadyExistAccountEmail_EMAIL_PARAMETER_IS_NOT_EXIST() throws DataStorageException, InternalServerErrorException {
        boolean expected = false;

        String email = "email";
        when(accountDAO.getAccountByEmail(email)).thenReturn(null);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountEmail(email);

        assertEquals(expected, result);
    }

    @Test()
    public void testAlreadyExistAccountEmail_EMAIL_PARAMETER_IS_EXIST() throws DataStorageException, InternalServerErrorException {
        boolean expected = true;

        String email = "email";
        when(accountDAO.getAccountByEmail(email)).thenReturn(account);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountEmail(email);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountName_NULL_NAME_PARAMETER() throws InternalServerErrorException {
        String name = null;
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountName(name);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountName_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        String name = "name";
        when(accountDAO.getAccountByName(name)).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountName(name);
    }

    @Test()
    public void testAlreadyExistAccountName_NAME_PARAMETER_IS_NOT_EXIST() throws DataStorageException, InternalServerErrorException {
        boolean expected = false;

        String name = "name";
        when(accountDAO.getAccountByName(name)).thenReturn(null);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountName(name);

        assertEquals(expected, result);
    }

    @Test()
    public void testAlreadyExistAccountName_NAME_PARAMETER_IS_EXIST() throws DataStorageException, InternalServerErrorException {
        boolean expected = true;

        String name = "name";
        when(accountDAO.getAccountByName(name)).thenReturn(account);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountName(name);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateAccountAuthToken_NULL_ACCOUNT_DETAILS_PARAMETER() throws InternalServerErrorException {
        AccountDetails accountDetails = null;
        authenticateAndAuthorizationServiceImpl.createAccountAuthToken(accountDetails);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateAccountAuthToken_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        AccountDetails accountDetails = new AccountDetails();
        when(accountAuthTokenDAO.createAccountAuthToken(any())).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.createAccountAuthToken(accountDetails);
    }

    @Test()
    public void testCreateAccountAuthToken_SHOUL_CREATE_ACCOUNT_AUTH_TOKEN() throws InternalServerErrorException, DataStorageException {
        AccountAuthToken expected = new AccountAuthToken();
        expected.setId(1L);
        expected.setSelector("random");
        expected.setValidator("random");
        expected.setAccountId(1);

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(1);
        mockStatic(DataUtil.class);
        when(DataUtil.generateRandomString()).thenReturn("random");
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        when(accountAuthTokenDAO.createAccountAuthToken(any())).thenReturn(1L);
        AccountAuthToken result = authenticateAndAuthorizationServiceImpl.createAccountAuthToken(accountDetails);

        assertEquals(expected.toString(), result.toString());
    }


    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountAuthToken_NULL_SELECTOR_PARAMETER() throws InternalServerErrorException {
        String selector = null;
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountAuthToken(selector);
    }


    @Test(expected = InternalServerErrorException.class)
    public void testAlreadyExistAccountAuthToken_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        String selector = null;
        when(accountAuthTokenDAO.getAccountAuthTokenBySelector(selector)).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.alreadyExistAccountAuthToken(selector);
    }


    @Test
    public void testAlreadyExistAccountAuthToken_SELECTOR_PARAMETER_IS_NOT_EXIST() throws InternalServerErrorException, DataStorageException {
        boolean expected = false;

        String selector = "selector";
        when(accountAuthTokenDAO.getAccountAuthTokenBySelector(selector)).thenReturn(null);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountAuthToken(selector);

        assertEquals(expected, result);
    }

    @Test
    public void testAlreadyExistAccountAuthToken_SELECTOR_PARAMETER_IS_EXIST() throws InternalServerErrorException, DataStorageException {
        boolean expected = true;

        String selector = "selector";
        when(accountAuthTokenDAO.getAccountAuthTokenBySelector(selector)).thenReturn(accountAuthToken);
        boolean result = authenticateAndAuthorizationServiceImpl.alreadyExistAccountAuthToken(selector);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateValidator_NULL_ACCOUNT_AUTH_TOKEN_PARAMETER() throws InternalServerErrorException {
        AccountAuthToken accountAuthToken = null;
        authenticateAndAuthorizationServiceImpl.updateValidator(accountAuthToken);
    }

    @Test
    public void testUpdateValidator_SHOULD_UPDATE_VALIDATOR() throws InternalServerErrorException {
        String actual = "actual";
        AccountAuthToken accountAuthToken = new AccountAuthToken();
        accountAuthToken.setValidator(actual);
        accountAuthToken.setId(1L);

        mockStatic(DataUtil.class);
        when(DataUtil.generateRandomString()).thenReturn("");
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        String result = authenticateAndAuthorizationServiceImpl.updateValidator(accountAuthToken);

        assertNotEquals(actual, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateValidator_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        AccountAuthToken accountAuthToken = new AccountAuthToken();
        accountAuthToken.setId(1L);
        mockStatic(DataUtil.class);
        when(DataUtil.generateRandomString()).thenReturn("");
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        doThrow(new DataStorageException("")).when(accountAuthTokenDAO).updateAccountAuthToken(any(AccountAuthToken.class),anyLong());
        authenticateAndAuthorizationServiceImpl.updateValidator(accountAuthToken);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetAccountAuthToken_NULL_SELECTOR_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        String selector = null;
        String validator = "";
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetAccountAuthToken_NULL_VALIDATOR_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        String selector = "";
        String validator = null;
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetAccountAuthToken_NULL_VALIDATOR_AND_NULL_SELECTOR_PARAMETERS() throws AccessDeniedException, InternalServerErrorException {
        String selector = null;
        String validator = null;
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetAccountAuthToken_DAO_LAYER_THROW_EXCEPTION() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        String selector = "";
        String validator = "";
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        when(accountAuthTokenDAO.getAccountAuthTokenBySelectorAndValidator(anyString(), anyString())).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
    }

    @Test(expected = AccessDeniedException.class)
    public void testGetAccountAuthToken_DAO_LAYER_RETURN_NULL() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        String selector = "";
        String validator = "";
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(anyString())).thenReturn("");
        when(accountAuthTokenDAO.getAccountAuthTokenBySelectorAndValidator(anyString(), anyString())).thenReturn(null);
        authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
    }

    @Test
    public void testGetAccountAuthToken_SHOUL_RETURN_ACCOUNT_AUTH_TOKEN() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        String selector = "selector";
        String validator = "validator";
        String securedValidator = "securedValidator";
        mockStatic(DataUtil.class);
        when(DataUtil.generateSecuredPassword(validator)).thenReturn(securedValidator);
        when(accountAuthTokenDAO.getAccountAuthTokenBySelectorAndValidator(selector, securedValidator)).thenReturn(accountAuthToken);
        AccountAuthToken result = authenticateAndAuthorizationServiceImpl.getAccountAuthToken(selector, validator);
        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInByIsRememberMe_NULL_ACCOUNT_AUTH_TOKEN_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        AccountAuthToken accountAuthToken = null;
        authenticateAndAuthorizationServiceImpl.signInByIsRememberMe(accountAuthToken);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInByIsRememberMe_DAO_LAYER_THROW_EXCEPTION() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        when(accountAuthToken.getAccountId()).thenReturn(1);
        when(accountDAO.getAccountById(anyInt())).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.signInByIsRememberMe(accountAuthToken);
    }

    @Test(expected = AccessDeniedException.class)
    public void testSignInByIsRememberMe() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        when(accountAuthToken.getAccountId()).thenReturn(1);
        when(accountDAO.getAccountById(anyInt())).thenReturn(null);
        authenticateAndAuthorizationServiceImpl.signInByIsRememberMe(accountAuthToken);
    }

    @Test
    public void testSignInByIsRememberMe_SHOUL_RETURN_ACCOUNT_DETAILS() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        Account account = getAccount();
        when(accountAuthToken.getAccountId()).thenReturn(1);
        when(accountDAO.getAccountById(anyInt())).thenReturn(account);
        mockStatic(DataUtil.class);
        when(DataUtil.generateUId(anyString())).thenReturn("");
        AccountDetails result = authenticateAndAuthorizationServiceImpl.signInByIsRememberMe(accountAuthToken);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInByManually_NULL_SIGN_IN_FORM_PARAMETER() throws AccessDeniedException, InternalServerErrorException, ValidationException {
        SignInForm signInForm = null;
        authenticateAndAuthorizationServiceImpl.signInByManually(signInForm);
    }

    @Test(expected = ValidationException.class)
    public void testSignInByManually_SIGN_IN_FORM_HAS_ERRORS() throws AccessDeniedException, InternalServerErrorException, ValidationException {
        SignInForm signInForm = mock(SignInForm.class);
        when(signInForm.getViolations()).thenReturn(mock(Violations.class));
        when(signInForm.getViolations().hasErrors()).thenReturn(true);
        authenticateAndAuthorizationServiceImpl.signInByManually(signInForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInByManually_DAO_LAYER_THROW_EXCEPTION() throws AccessDeniedException, InternalServerErrorException, ValidationException, DataStorageException {
        SignInForm signInForm = mock(SignInForm.class);
        when(signInForm.getPassword()).thenReturn("");
        when(signInForm.getEmail()).thenReturn("");
        when(signInForm.getViolations()).thenReturn(mock(Violations.class));
        when(signInForm.getViolations().hasErrors()).thenReturn(false);
        when(accountDAO.getAccountByEmailAndPassword(anyString(),anyString())).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.signInByManually(signInForm);
    }

    @Test(expected = AccessDeniedException.class)
    public void testSignInByManually_DAO_LAYER_RETURN_NULL() throws AccessDeniedException, InternalServerErrorException, ValidationException, DataStorageException {
        SignInForm signInForm = mock(SignInForm.class);
        when(signInForm.getPassword()).thenReturn("");
        when(signInForm.getEmail()).thenReturn("");
        when(signInForm.getViolations()).thenReturn(mock(Violations.class));
        when(signInForm.getViolations().hasErrors()).thenReturn(false);
        when(accountDAO.getAccountByEmailAndPassword(anyString(),anyString())).thenReturn(null);
        authenticateAndAuthorizationServiceImpl.signInByManually(signInForm);
    }

    @Test
    public void testSignInByManually_SHOUL_RETURN_ACCOUNT_DETAILS() throws AccessDeniedException, InternalServerErrorException, ValidationException, DataStorageException {
        Account account = getAccount();
        SignInForm signInForm = mock(SignInForm.class);
        when(signInForm.getPassword()).thenReturn("");
        when(signInForm.getEmail()).thenReturn("");
        when(signInForm.getViolations()).thenReturn(mock(Violations.class));
        when(signInForm.getViolations().hasErrors()).thenReturn(false);
        when(accountDAO.getAccountByEmailAndPassword(anyString(),anyString())).thenReturn(account);
        AccountDetails result = authenticateAndAuthorizationServiceImpl.signInByManually(signInForm);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInBySocial_NULL_SOCIAL_ACCOUNT_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        SocialAccount socialAccount = null;
        authenticateAndAuthorizationServiceImpl.signInBySocial(socialAccount);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignInBySocial_DAO_LAYER_THROW_EXCEPTION() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        SocialAccount socialAccount = mock(SocialAccount.class);
        when(socialAccount.getEmail()).thenReturn("");
        when(accountDAO.getAccountByEmail(anyString())).thenThrow(new DataStorageException(""));
        authenticateAndAuthorizationServiceImpl.signInBySocial(socialAccount);
    }

    @Test(expected = AccessDeniedException.class)
    public void testSignInBySocial_DAO_LAYER_RETURN_NULL() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        SocialAccount socialAccount = mock(SocialAccount.class);
        when(socialAccount.getEmail()).thenReturn("");
        when(accountDAO.getAccountByEmail(anyString())).thenReturn(null);
        authenticateAndAuthorizationServiceImpl.signInBySocial(socialAccount);
    }

    @Test
    public void testSignInBySocial_SHOUL_RETURN_ACCOUNT_DETAILS() throws AccessDeniedException, InternalServerErrorException, DataStorageException {
        Account account = getAccount();
        SocialAccount socialAccount = mock(SocialAccount.class);
        when(socialAccount.getEmail()).thenReturn("");
        when(accountDAO.getAccountByEmail(anyString())).thenReturn(account);
        AccountDetails result = authenticateAndAuthorizationServiceImpl.signInBySocial(socialAccount);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testSignUpByManually_NULL_SIGN_UP_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        SignUpForm signUpForm = null;
        authenticateAndAuthorizationServiceImpl.signUpByManually(signUpForm);
    }

    @Test(expected = ValidationException.class)
    public void testSignUpByManually_SIGN_UP_FORM_HAS_ERRORS() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        SignUpForm signUpForm = mock(SignUpForm.class);
        when(signUpForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpForm.getViolations().hasErrors()).thenReturn(true);
        authenticateAndAuthorizationServiceImpl.signUpByManually(signUpForm);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testSignUpByManually_SIGN_UP_METHOD_RETURN_NULL() throws Exception {
        SignUpForm signUpForm = mock(SignUpForm.class);
        when(signUpForm.getEmail()).thenReturn("");
        when(signUpForm.getPassword()).thenReturn("");
        when(signUpForm.getName()).thenReturn("");
        when(signUpForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpForm.getViolations().hasErrors()).thenReturn(false);
        when(authenticateAndAuthorizationServiceImpl, "singUp","","","").thenReturn(null);
        authenticateAndAuthorizationServiceImpl.signUpByManually(signUpForm);
    }

    @Test
    public void testSignUpByManually_SHOULD_RETURN_ACCOUNT_DETAILS() throws Exception {
        Account account = getAccount();
        SignUpForm signUpForm = mock(SignUpForm.class);
        when(signUpForm.getEmail()).thenReturn("");
        when(signUpForm.getPassword()).thenReturn("");
        when(signUpForm.getName()).thenReturn("");
        when(signUpForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpForm.getViolations().hasErrors()).thenReturn(false);
        when(authenticateAndAuthorizationServiceImpl, "singUp","","","").thenReturn(account);
        AccountDetails result = authenticateAndAuthorizationServiceImpl.signUpByManually(signUpForm);

        assertNotNull(result);
    }


    @Test(expected = InternalServerErrorException.class)
    public void testsSignUpBySocial_NULL_SOCIAL_ACCOUNT_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        SocialAccount socialAccount = null;
        SignUpWithSocialForm signUpWithSocialForm = mock(SignUpWithSocialForm.class);
        authenticateAndAuthorizationServiceImpl.signUpBySocial(socialAccount,signUpWithSocialForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testsSignUpBySocial_NULL_SIGN_UP_WITH_SOCIAL_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        SocialAccount socialAccount = mock(SocialAccount.class);
        SignUpWithSocialForm signUpWithSocialForm = null;
        authenticateAndAuthorizationServiceImpl.signUpBySocial(socialAccount,signUpWithSocialForm);
    }

    @Test(expected = ValidationException.class)
    public void testsSignUpBySocial_NULL_SIGN_UP_WITH_SOCIAL_FORM_HAS_ERRORS() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        SocialAccount socialAccount = mock(SocialAccount.class);
        when(socialAccount.getEmail()).thenReturn("");
        SignUpWithSocialForm signUpWithSocialForm = mock(SignUpWithSocialForm.class);
        when(signUpWithSocialForm.getName()).thenReturn("");
        when(signUpWithSocialForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpWithSocialForm.getViolations().hasErrors()).thenReturn(true);
        authenticateAndAuthorizationServiceImpl.signUpBySocial(socialAccount,signUpWithSocialForm);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testsSignUpBySocial_SIGN_UP_METHOD_RETURN_NULL() throws Exception {
        SocialAccount socialAccount = mock(SocialAccount.class);
        SignUpWithSocialForm signUpWithSocialForm = mock(SignUpWithSocialForm.class);
        when(signUpWithSocialForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpWithSocialForm.getViolations().hasErrors()).thenReturn(false);
        when(authenticateAndAuthorizationServiceImpl, "singUp","","","").thenReturn(null);
        authenticateAndAuthorizationServiceImpl.signUpBySocial(socialAccount,signUpWithSocialForm);
    }

    @Test
    public void testsSignUpBySocial_SHOULD_RETURN_ACCOUNT_DETAILS() throws Exception {
        Account account = getAccount();
        SocialAccount socialAccount = mock(SocialAccount.class);
        SignUpWithSocialForm signUpWithSocialForm = mock(SignUpWithSocialForm.class);
        when(signUpWithSocialForm.getViolations()).thenReturn(mock(Violations.class));
        when(signUpWithSocialForm.getViolations().hasErrors()).thenReturn(false);
        when(authenticateAndAuthorizationServiceImpl, "singUp","","","").thenReturn(account);
        AccountDetails result = authenticateAndAuthorizationServiceImpl.signUpBySocial(socialAccount, signUpWithSocialForm);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testIsSecuredUrl_NULL_URL_PARAMETER() throws InternalServerErrorException {
        String url = null;
        authenticateAndAuthorizationServiceImpl.isSecuredUrl(url);
    }

    @Test
    public void testIsSecuredUrl_CHECKED_URL_IS_SECURED() throws InternalServerErrorException {
        String checkedUrl = "/some/url";
        String securedURL = "/some/url";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.hasUrl(securedURL)).thenReturn(true);
        boolean result = authenticateAndAuthorizationServiceImpl.isSecuredUrl(checkedUrl);

        assertTrue(result);
    }

    @Test
    public void testIsSecuredUrl_CHECKED_URL_IS_SECURED_WITH_1_ADDITIONAL_URL() throws InternalServerErrorException {
        String checkedUrl = "/some/url";
        String additionalUrl = "/some";
        String securedURL = "/some/url/*";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.hasUrl(securedURL)).thenReturn(true);
        boolean result = authenticateAndAuthorizationServiceImpl.isSecuredUrl(checkedUrl.concat(additionalUrl));

        assertTrue(result);
    }

    @Test
    public void testIsSecuredUrl_CHECKED_URL_IS_SECURED_WITH_2_ADDITIONAL_URL() throws InternalServerErrorException {
        String checkedUrl = "/some/url";
        String additional1Url = "/some";
        String additional2Url = "/some";
        String securedURL = "/some/url/*";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.hasUrl(securedURL)).thenReturn(true);
        boolean result = authenticateAndAuthorizationServiceImpl.isSecuredUrl(checkedUrl.concat(additional1Url).concat(additional2Url));

        assertFalse(result);
    }

    @Test(expected = AccessDeniedException.class)
    public void testIsAuthorized_NULL_ACCOUNT_DETAILS_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        AccountDetails accountDetails = null;
        String url = "";
        authenticateAndAuthorizationServiceImpl.isAuthorized(accountDetails, url);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testIsAuthorized_NULL_URL_PARAMETER() throws AccessDeniedException, InternalServerErrorException {
        AccountDetails accountDetails = mock(AccountDetails.class);
        String url = null;
        authenticateAndAuthorizationServiceImpl.isAuthorized(accountDetails, url);
    }

    @Test(expected = AccessDeniedException.class)
    public void testIsAuthorized_NOT_CONTAINS_URL_FOR_ROLE() throws AccessDeniedException, InternalServerErrorException {
        AccountDetails accountDetails = mock(AccountDetails.class);
        when(accountDetails.getRole()).thenReturn("USER");
        String url = "/some/url";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.getUrlForRole(anyString())).thenReturn(Collections.emptyList());
        authenticateAndAuthorizationServiceImpl.isAuthorized(accountDetails, url);
    }

    @Test
    public void testIsAuthorized_CONTAINS_URL_FOR_ROLE() throws AccessDeniedException, InternalServerErrorException {
        AccountDetails accountDetails = mock(AccountDetails.class);
        when(accountDetails.getRole()).thenReturn("USER");
        String url = "/some/url";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.getUrlForRole(anyString())).thenReturn(List.of("/some/url","/another/url"));
        boolean result = authenticateAndAuthorizationServiceImpl.isAuthorized(accountDetails, url);

        assertTrue(result);
    }

    @Test
    public void testIsAuthorized_CONTAINS_URL_WITH_1_ADDITIONAL_URL_FOR_ROLE() throws AccessDeniedException, InternalServerErrorException {
        AccountDetails accountDetails = mock(AccountDetails.class);
        when(accountDetails.getRole()).thenReturn("USER");
        String url = "/some/url/addition";
        mockStatic(SecurityConfiguration.class);
        when(SecurityConfiguration.getUrlForRole(anyString())).thenReturn(List.of("/some/url/*","/another/url"));
        boolean result = authenticateAndAuthorizationServiceImpl.isAuthorized(accountDetails, url);

        assertTrue(result);
    }

    private Account getAccount(){
        Account account = new Account();
        account.setId(1);
        account.setName("");
        account.setPassword("");
        account.setEmail("");
        Role role = new Role();
        role.setName("");
        account.setRole(role);
        return account;
    }
}
