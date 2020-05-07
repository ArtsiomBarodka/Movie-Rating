package epam.my.project.controller.filter;

import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.util.WebUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "RestoreCurrentAccountDetailsFilter")
public class RestoreCurrentAccountDetailsFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!WebUtil.isCurrentAccountDetailsCreated(request) && WebUtil.hasSelectorCookie(request)){
            String selector = String.valueOf(WebUtil.getSelectorCookie(request));
            String validator = String.valueOf(WebUtil.getValidatorCookie(request));
            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountAuthToken(selector)){
                AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().getAccountAuthToken(selector, validator);
                if(Objects.nonNull(accountAuthToken)){
                    AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInByIsRememberMe(accountAuthToken);
                    if(Objects.nonNull(accountDetails)){
                        WebUtil.setCurrentAccountDetails(request, accountDetails);
                        String newValidator = serviceFactory.getAuthenticateAndAuthorizationService().updateValidator(accountAuthToken);
                        WebUtil.setValidatorCookie(response, newValidator);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }


}
