package epam.my.project.service.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.RoleDAO;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.DataStorageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Account;
import epam.my.project.model.form.SignInForm;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.validation.ValidatorFactory;
import epam.my.project.service.AuthenticateAndAuthorizationService;
import epam.my.project.util.DataUtil;
import java.util.List;
import java.util.Objects;



public class AuthenticateAndAuthorizationServiceImpl implements AuthenticateAndAuthorizationService {
    private AccountDAO accountDAO;
    private RoleDAO roleDAO;

    public AuthenticateAndAuthorizationServiceImpl(DAOFactory daoFactory) {
        this.accountDAO = daoFactory.getAccountDAO();
        this.roleDAO = daoFactory.getRoleDAO();
    }

    @Override
    public boolean alreadyExistAccount(String email) throws InternalServerErrorException {
        try {
            Account account = accountDAO.getAccountByEmail(email);
            return Objects.nonNull(account);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signInByManually(SignInForm signInForm) throws InternalServerErrorException, AccessDeniedException {
        if(Objects.isNull(signInForm)) throw new InternalServerErrorException("Sign in form is null.");
        String securedPassword = DataUtil.generateSecuredPassword(signInForm.getPassword());
        try {
            Account account = accountDAO.getAccountByEmailAndPassword(signInForm.getEmail(), securedPassword);
            if(account == null){
                throw new AccessDeniedException("Account is not exist. You have to sign up before.");
            }
            return fetchAccountDetails(account);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signInBySocial(SocialAccount socialAccount) throws InternalServerErrorException, AccessDeniedException {
        if(Objects.isNull(socialAccount)) throw new InternalServerErrorException("Social account is null.");
        try {
            Account account = accountDAO.getAccountByEmail(socialAccount.getEmail());
            if(account == null){
                throw new AccessDeniedException("Account is not exist. You have to sign up before.");
            }
            return fetchAccountDetails(account);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public AccountDetails signUpByManually(SignUpForm signUpForm) throws ValidationException, InternalServerErrorException, ObjectNotFoundException {
        if(Objects.isNull(signUpForm)) throw new InternalServerErrorException("Sign up form is null.");
        String securedPassword = DataUtil.generateSecuredPassword(signUpForm.getPassword());
        Account account = singUp(signUpForm.getName(), signUpForm.getEmail(), securedPassword);
        return fetchAccountDetails(account);
    }

    @Override
    public AccountDetails SignUpBySocial(SocialAccount socialAccount, String accountName) throws ValidationException, InternalServerErrorException {
        if(Objects.isNull(socialAccount)) throw new InternalServerErrorException("Social account is null.");
        if(!ValidatorFactory.NAME_VALIDATOR.validate(accountName)){
            throw new ValidationException("Invalid name value : " + accountName, "name");
        }
        String securedPassword = DataUtil.generateRandomPassword();
        Account account = singUp(accountName, socialAccount.getEmail(),securedPassword);
        return fetchAccountDetails(account);
    }

    private Account singUp(String name, String email, String password) throws ValidationException, InternalServerErrorException {
        try{
            Account account = accountDAO.getAccountByEmail(email);
            if(account != null){
                throw new ValidationException("Email already exist: " + email, "email");
            }
            account = accountDAO.getAccountByName(name);
            if(account != null){
                throw new ValidationException("Name already exist: " + name, "name");
            }
            return createAccountForUser(name, email, password);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get account from dao layer.", e);
        }
    }

    @Override
    public boolean isSecuredUrl(String url) throws InternalServerErrorException {
        if(url == null) throw new InternalServerErrorException("Invalid url value.");
        return SecurityConfiguration.hasUrl(url);
    }

    @Override
    public boolean isAuthorized(AccountDetails accountDetails, String url) throws InternalServerErrorException, AccessDeniedException {
        if(url == null) throw new InternalServerErrorException("Invalid url value.");
        if(accountDetails == null) throw new AccessDeniedException("You are not allowed to view this page.");
        List<String> urlPatternsForRole = SecurityConfiguration.getUrlForRole(accountDetails.getRole());
        if(!urlPatternsForRole.contains(url)) throw new AccessDeniedException("You are not allowed to view this page.");
        return true;
    }

    private AccountDetails fetchAccountDetails(Account account){
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setRoles(account.getRole().getName());
        return accountDetails;
    }

    private Account createAccountForUser(String name, String email, String password) throws InternalServerErrorException {
        try {
            Account account = new Account();
            account.setName(name);
            account.setEmail(email);
            account.setPassword(password);
            account.setRole(roleDAO.getRoleByName(SecurityConfiguration.ROLE_USER));
            return accountDAO.createAccount(account);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get entity from dao layer.", e);
        }
    }
}
