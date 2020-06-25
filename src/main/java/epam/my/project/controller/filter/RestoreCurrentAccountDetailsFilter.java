package epam.my.project.controller.filter;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.PageException;
import epam.my.project.exception.InternalServerErrorException;
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
            String selector = WebUtil.getSelectorCookie(request).getValue();
            String validator = WebUtil.getValidatorCookie(request).getValue();
            try {
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
            } catch (AccessDeniedException e) {
                throw new PageException(e.getMessage(), e, HttpServletResponse.SC_FORBIDDEN);
            } catch (InternalServerErrorException e) {
                throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        chain.doFilter(request, response);
    }


}
