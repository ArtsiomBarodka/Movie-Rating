package epam.my.project.controller.filter;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.PageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.util.WebUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        try {
            if(serviceFactory.getAuthenticateAndAuthorizationService().isSecuredUrl(url)){
                AccountDetails accountDetails = WebUtil.getCurrentAccountDetails(request);
                if(serviceFactory.getAuthenticateAndAuthorizationService().isAuthorized(accountDetails, url)){
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (InternalServerErrorException e) {
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AccessDeniedException e) {
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
