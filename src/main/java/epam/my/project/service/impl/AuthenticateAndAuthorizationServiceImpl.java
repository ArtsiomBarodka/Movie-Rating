package epam.my.project.service.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.RoleDAO;
import epam.my.project.dao.impl.AccountDAOImp;
import epam.my.project.dao.impl.RoleDAOImpl;
import epam.my.project.entity.Account;
import epam.my.project.entity.User;
import epam.my.project.form.SignInForm;
import epam.my.project.form.SignUpForm;
import epam.my.project.model.AccountDetails;
import epam.my.project.model.SocialAccount;
import epam.my.project.service.AuthenticateAndAuthorizationService;
import epam.my.project.service.UserService;
import epam.my.project.util.DataUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuthenticateAndAuthorizationServiceImpl implements AuthenticateAndAuthorizationService {
    @Override
    public AccountDetails signInByManually(SignInForm signInForm) throws SQLException {
        //validate
        String securedPassword = DataUtil.generateSecuredPassword(signInForm.getPassword());
        return signIn(signInForm.getEmail(), securedPassword);
    }

    @Override
    public AccountDetails signInBySocial(SocialAccount socialAccount) throws SQLException {
        String securedPassword = DataUtil.generateSecuredPassword(socialAccount.getEmail());
        return signIn(socialAccount.getEmail(), securedPassword);
    }

    private AccountDetails signIn(String email, String password) throws SQLException {
        AccountDAO accountDAO = new AccountDAOImp();
        Account account = accountDAO.getAccountByEmailAndPassword(email, password);
        if(account == null){
            return null;
        }
        return fetchAccountDetails(account);
    }

    @Override
    public AccountDetails signUpByManually(SignUpForm signUpForm) throws SQLException, IOException {
        //validate
        UserService userService = new UserServiceImpl();
        String securedPassword = DataUtil.generateSecuredPassword(signUpForm.getPassword());
        Account account = singUp(signUpForm.getName(), signUpForm.getEmail(), securedPassword);
        User user = userService.createUser(account,null);
        return fetchAccountDetails(account);
    }

    @Override
    public AccountDetails SignUpBySocial(SocialAccount socialAccount, String accountName) throws SQLException, IOException {
        //validate accountName
        UserService userService = new UserServiceImpl();
        String securedPassword = DataUtil.generateSecuredPassword(socialAccount.getEmail());
        Account account = singUp(accountName, socialAccount.getEmail(),securedPassword);
        User user = userService.createUser(account, socialAccount.getAvatarUrl());
        return fetchAccountDetails(account);
    }

    private Account singUp(String name, String email, String password) throws SQLException {
        AccountDAO accountDAO = new AccountDAOImp();
        Account account = accountDAO.getAccountByEmail(email);
        if(account != null){
            //validation exceptiont
            return null;
        }
        account = accountDAO.getAccountByName(name);
        if(account != null){
            //validation exceptiont
            return null;
        }
        return createAccountForUser(name, email, password);
    }

    @Override
    public boolean isAuthorized(AccountDetails accountDetails, String url){
        if(url == null) return false;
        boolean needUrlForAuthorize = SecurityConfiguration.hasUrl(url);
        if(!needUrlForAuthorize) return true;
        if(accountDetails == null) return false;
        List<String> urlPatternsForRole = SecurityConfiguration.getUrlForRole(accountDetails.getRole());
        return urlPatternsForRole.contains(url);
    }

    private AccountDetails fetchAccountDetails(Account account){
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(account.getId());
        accountDetails.setName(account.getName());
        accountDetails.setRoles(account.getRole().getName());
        return accountDetails;
    }

    private Account createAccountForUser(String name, String email, String password) throws SQLException {
        AccountDAO accountDAO = new AccountDAOImp();
        RoleDAO roleDAO = new RoleDAOImpl();

        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setPassword(password);
        account.setRole(roleDAO.getRoleByName(SecurityConfiguration.ROLE_USER));

        return accountDAO.createAccount(account);
    }

}
