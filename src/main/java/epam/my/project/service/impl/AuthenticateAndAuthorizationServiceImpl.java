package epam.my.project.service.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.RoleDAO;
import epam.my.project.dao.UserDAO;
import epam.my.project.dao.impl.AccountDAOImp;
import epam.my.project.dao.impl.RoleDAOImpl;
import epam.my.project.dao.impl.UserDAOImpl;
import epam.my.project.entity.Account;
import epam.my.project.form.SignInForm;
import epam.my.project.form.SignUpForm;
import epam.my.project.model.Principal;

import java.sql.SQLException;
import java.util.List;

public class AuthenticateAndAuthorizationServiceImpl {
    public Principal signIn(SignInForm signInForm) throws SQLException {
        //validate
        AccountDAO accountDAO = new AccountDAOImp();
        Account account = accountDAO.getAccountByEmailAndPassword(signInForm.getEmail(), signInForm.getPassword());
        if(account == null){
            return null;
        }
        Principal principal = new Principal();
        principal.setId(account.getId());
        principal.setName(account.getName());
        principal.setRoles(account.getRole().getName());
        return principal;
    }

    public Principal signUpByManually(SignUpForm signInForm) throws SQLException {
        //validate
        AccountDAO accountDAO = new AccountDAOImp();
        RoleDAO roleDAO = new RoleDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        Account account = accountDAO.getAccountByEmailAndPassword(signInForm.getEmail(), signInForm.getPassword());
        if(account == null){
            return null;
        }

        account = new Account();
        account.setName(signInForm.getName());
        account.setEmail(signInForm.getName());
        account.setPassword(signInForm.getPassword());
        account.setRole(roleDAO.getRoleByName(SecurityConfiguration.ROLE_USER));
        account = accountDAO.createAccount(account);
        userDAO.createUser(account.getId());
        Principal principal = new Principal();
        principal.setId(account.getId());
        principal.setName(account.getName());
        principal.setRoles(account.getRole().getName());
        return principal;
    }

    public boolean isAuthorized(Principal principal, String url){
        if(url == null) return false;
        if(principal == null) return !SecurityConfiguration.hasUrl(url);

        List<String> urlPatternsForRole = SecurityConfiguration.getUrlForRole(principal.getRole());
        return urlPatternsForRole.contains(url);
    }
}
