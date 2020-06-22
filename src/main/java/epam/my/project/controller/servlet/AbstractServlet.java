package epam.my.project.controller.servlet;

import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

public abstract class AbstractServlet extends HttpServlet {
    protected final Logger logger = getLogger(getClass());
    protected ServiceFactory serviceFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
        logger.info("Servlet was initialized");
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
}
