package epam.my.project.service.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.dao.AccountAuthTokenDAO;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.RoleDAO;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.service.exception.AccessDeniedException;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.entity.Account;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.entity.Role;
import epam.my.project.model.form.SignInForm;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.form.SignUpWithSocialForm;
import epam.my.project.service.AuthenticateAndAuthorizationService;
import epam.my.project.util.DataUtil;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Authenticate and authorization service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class AuthenticateAndAuthorizationServiceImpl implements AuthenticateAndAuthorizationService {
    private AccountDAO accountDAO;
    private AccountAuthTokenDAO accountAuthTokenDAO;
    private RoleDAO roleDAO;

    /**
     * Instantiates a new Authenticate and authorization service.
     *
     * @param accountDAO          the account dao
     * @param accountAuthTokenDAO the account auth token dao
     * @param roleDAO             the role dao
     */
    AuthenticateAndAuthorizationServiceImpl(AccountDAO accountDAO, AccountAuthTokenDAO accountAuthTokenDAO, RoleDAO roleDAO) {
        this.accountDAO = accountDAO;
        this.accountAuthTokenDAO = accountAuthTokenDAO;
        this.roleDAO = roleDAO;
    }

    /**
     * Instantiates a new Authenticate and authorization service.
     *
     * @param daoFactory the dao factory
     */
    AuthenticateAndAuthorizationServiceImpl(DAOFactory daoFactory) {
        this.accountDAO = daoFactory.getAccountDAO();
        this.accountAuthTokenDAO = daoFactory.getAccountAuthTokenDAO();
        this.roleDAO = daoFactory.getRoleDAO();
    }

    @Override
    public boolean alreadyExistAccountEmail(String email) throws InternalServerErrorException {
        if(Objects.isNull(email)) throw new InternalServerErrorException("Validate is null.");
        try {
            Optional<Account> account = accountDAO.getAccountByEmail(email);
            return account.isPresent();
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public boolean alreadyExistAccountName(String name) throws InternalServerErrorException {
        if(Objects.isNull(name)) throw new InternalServerErrorException("Name is null.");
        try {
            Optional<Account> account = accountDAO.getAccountByName(name);
            return account.isPresent();
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
        public AccountAuthToken createAccountAuthToken(AccountDetails accountDetails) throws InternalServerErrorException {
            if(Objects.isNull(accountDetails)) throw new InternalServerErrorException("Account details is null.");
            try {
                String validator = DataUtil.generateRandomString();
                String securedValidator = DataUtil.generateSecuredPassword(validator);

                AccountAuthToken accountAuthToken = new AccountAuthToken();
                accountAuthToken.setAccountId(accountDetails.getId());
                accountAuthToken.setSelector(DataUtil.generateRandomString());
                accountAuthToken.setValidator(securedValidator);
                accountAuthToken.setId(accountAuthTokenDAO.createAccountAuthToken(accountAuthToken));
                accountAuthToken.setValidator(validator);

                return accountAuthToken;
            } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account authentication token from dao layer.", e);
        }
    }

    @Override
    public boolean alreadyExistAccountAuthToken(String selector) throws InternalServerErrorException {
        if(Objects.isNull(selector)) throw new InternalServerErrorException("Selector is null.");
        try {
            Optional<AccountAuthToken> accountAuthToken = accountAuthTokenDAO.getAccountAuthTokenBySelector(selector);
            return accountAuthToken.isPresent();
        } catch (DataStorageException e) {
            throw new InternalServerErrorException("Can`t get account authentication token from dao layer.", e);
        }
    }

    @Override
    public String updateValidator(AccountAuthToken accountAuthToken) throws InternalServerErrorException {
        if(Objects.isNull(accountAuthToken)) throw new InternalServerErrorException("Account authentication token is null.");
        try {
            String validator = DataUtil.generateRandomString();
            String securedValidator = DataUtil.generateSecuredPassword(validator);
            accountAuthToken.setValidator(securedValidator);
            accountAuthTokenDAO.updateAccountAuthToken(accountAuthToken, accountAuthToken.getId());
            return validator;
        } catch (DataStorageException e) {
            throw new InternalServerErrorException("Can`t update account authentication token in dao layer.", e);
        }
    }

    @Override
    public AccountAuthToken getAccountAuthToken(String selector, String validator) throws InternalServerErrorException, AccessDeniedException {
        if(Objects.isNull(selector)) throw new InternalServerErrorException("Selector is null.");
        if(Objects.isNull(validator)) throw new InternalServerErrorException("Validator is null.");
        try {
            String securedValidator = DataUtil.generateSecuredPassword(validator);
            Optional<AccountAuthToken> accountAuthToken = accountAuthTokenDAO.getAccountAuthTokenBySelectorAndValidator(selector, securedValidator);
            return accountAuthToken.orElseThrow(()-> new AccessDeniedException("Account authentication token is not exist. You have to sign up before."));
        } catch (DataStorageException e) {
            throw new InternalServerErrorException("Can`t get account authentication token from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signInByIsRememberMe(AccountAuthToken accountAuthToken) throws InternalServerErrorException, AccessDeniedException {
        if(Objects.isNull(accountAuthToken)) throw new InternalServerErrorException("Account authentication token is null.");
        try {
            Optional<Account> account = accountDAO.getAccountById(accountAuthToken.getAccountId());
            return fetchAccountDetails(account.orElseThrow(
                            ()-> new AccessDeniedException("Account is not exist. You have to sign up before.")));
        } catch (DataStorageException e) {
            throw new InternalServerErrorException("Can`t get account authentication token from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signInByManually(SignInForm signInForm) throws InternalServerErrorException, AccessDeniedException, ValidationException {
        if(Objects.isNull(signInForm)) throw new InternalServerErrorException("Sign in form is null.");
        if(signInForm.getViolations().hasErrors()){
            throw new ValidationException("Sign in form has invalid inputs", signInForm.getViolations());
        }
        String securedPassword = DataUtil.generateSecuredPassword(signInForm.getPassword());
        try {
            Optional<Account> account = accountDAO.getAccountByEmailAndPassword(signInForm.getEmail(), securedPassword);
            return fetchAccountDetails(account.orElseThrow(
                    ()->new AccessDeniedException("Account is not exist. You have to sign up before.")));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signInBySocial(SocialAccount socialAccount) throws InternalServerErrorException, AccessDeniedException {
        if(Objects.isNull(socialAccount)) throw new InternalServerErrorException("Social account is null.");
        try {
            Optional<Account> account = accountDAO.getAccountByEmail(socialAccount.getEmail());
            return fetchAccountDetails(account.orElseThrow(
                    ()->new AccessDeniedException("Account is not exist. You have to sign up before.")));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signUpByManually(SignUpForm signUpForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException {
        if(Objects.isNull(signUpForm)) throw new InternalServerErrorException("Sign up form is null.");
        if(signUpForm.getViolations().hasErrors()){
            throw new ValidationException("Sign up form has invalid inputs", signUpForm.getViolations());
        }
        String securedPassword = DataUtil.generateSecuredPassword(signUpForm.getPassword());
        Optional<Account> account = singUp(signUpForm.getName(), signUpForm.getEmail(), securedPassword);
        return fetchAccountDetails(account.orElseThrow(
                ()->new ObjectNotFoundException("Account not found")));
    }

    @Override
    public AccountDetails signUpBySocial(SocialAccount socialAccount, SignUpWithSocialForm signUpWithSocialForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException {
        if(Objects.isNull(socialAccount)) throw new InternalServerErrorException("Social account is null.");
        if(Objects.isNull(signUpWithSocialForm)) throw new InternalServerErrorException("Sign up form is null.");
        if(signUpWithSocialForm.getViolations().hasErrors()){
            throw new ValidationException("Sign up social form has invalid inputs", signUpWithSocialForm.getViolations());
        }
        String securedPassword = DataUtil.generateRandomPassword();
        Optional<Account> account = singUp(signUpWithSocialForm.getName(), socialAccount.getEmail(),securedPassword);
        return fetchAccountDetails(account.orElseThrow(
                ()->new ObjectNotFoundException("Account not found")));
    }

    private Optional<Account> singUp(String name, String email, String password) throws  InternalServerErrorException {
        try{
            return createAccountForUser(name, email, password);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public boolean isSecuredUrl(String url) throws InternalServerErrorException {
        if(url == null) throw new InternalServerErrorException("Invalid url value.");
        if(SecurityConfiguration.hasUrl(url)){
            return true;
        }
        url = handleUrl(url);
        return SecurityConfiguration.hasUrl(url);
    }

    @Override
    public boolean isAuthorized(AccountDetails accountDetails, String url) throws InternalServerErrorException, AccessDeniedException {
        if(url == null) throw new InternalServerErrorException("Invalid url value.");
        if(accountDetails == null) throw new AccessDeniedException("You are not allowed to view this page.");
        List<String> urlPatternsForRole = SecurityConfiguration.getUrlForRole(accountDetails.getRole());
        if(urlPatternsForRole.contains(url)) {
            return true;
        }
        url = handleUrl(url);
        if(urlPatternsForRole.contains(url)) {
            return true;
        }
        throw new AccessDeniedException("You are not allowed to view this page.");
    }

    private String handleUrl(String url){
        if(url.contains("/") && url.length() > 2){
            url = url.substring(0, url.lastIndexOf('/')).concat("/*");
        }
        return url;
    }

    private AccountDetails fetchAccountDetails(Account account){
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setRole(account.getRole().getName());
        accountDetails.setUid(DataUtil.generateUId(account.getName()));
        return accountDetails;
    }

    private Optional<Account> createAccountForUser(String name, String email, String password) throws DataStorageException, InternalServerErrorException {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setPassword(password);
        Optional<Role> role = roleDAO.getRoleByName(SecurityConfiguration.ROLE_USER);
        if(role.isPresent()){
            account.setRole(role.get());
            return accountDAO.createAccount(account);
        }
        throw new InternalServerErrorException("Role is null");

    }
}
