package epam.my.project.controller.filter;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

public abstract class AbstractFilter implements Filter {
    protected final Logger logger = getLogger(getClass());
    protected ServiceFactory serviceFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        doFilter(req, resp, chain);
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    protected void redirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }

    protected void handleException(Exception e, HttpServletResponse response) throws IOException {
        if(e instanceof ObjectNotFoundException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            logger.warn(e.getMessage(), e);
        } else if(e instanceof RetrieveSocialAccountFailedException){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            logger.warn(e.getMessage(), e);
        } else if(e instanceof AccessDeniedException){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            logger.warn(e.getMessage(), e);
        } else if(e instanceof InternalServerErrorException){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage(), e);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
