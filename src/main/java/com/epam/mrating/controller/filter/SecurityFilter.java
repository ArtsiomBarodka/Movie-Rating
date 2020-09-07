package com.epam.mrating.controller.filter;

import com.epam.mrating.controller.exception.PageException;
import com.epam.mrating.service.exception.AccessDeniedException;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.util.WebUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Security filter.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        try {
            if(serviceFactory.getAuthenticateAndAuthorizationService().isSecuredUrl(url)){
                if(WebUtil.isSessionCurrentAccountDetailsCreated(request)){
                    AccountDetails accountDetails = WebUtil.getSessionCurrentAccountDetails(request);
                    if(serviceFactory.getAuthenticateAndAuthorizationService().isAuthorized(accountDetails, url)){
                        chain.doFilter(request, response);
                    }
                } else {
                    WebUtil.setSessionRedirectUrlAfterAuthenticate(request,request.getRequestURI());
                    ViewUtil.redirect("/app/show/sign-in", request,response);
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
