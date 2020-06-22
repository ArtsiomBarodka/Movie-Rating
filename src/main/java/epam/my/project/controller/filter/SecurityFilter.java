package epam.my.project.controller.filter;

import epam.my.project.model.domain.AccountDetails;
import epam.my.project.util.WebUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
        } catch (Exception e) {
           handleException(e, response);
        }
    }
}
