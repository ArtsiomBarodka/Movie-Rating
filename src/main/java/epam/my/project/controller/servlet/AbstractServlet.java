package epam.my.project.controller.servlet;

import epam.my.project.exception.*;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.view.factory.ViewFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import static org.apache.logging.log4j.LogManager.getLogger;

public abstract class AbstractServlet extends HttpServlet {
    protected final Logger logger = getLogger(getClass());
    protected ServiceFactory serviceFactory;
    protected ViewFactory viewFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
        this.viewFactory = ViewFactory.VIEW_FACTORY;
        logger.info("Servlet was initialized");
    }

    protected void handleException(Exception e) {
        if(e instanceof ObjectNotFoundException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_NOT_FOUND);
        } else if(e instanceof RetrieveSocialAccountFailedException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_UNAUTHORIZED);
        } else if(e instanceof AccessDeniedException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_FORBIDDEN);
        } else if(e instanceof InternalServerErrorException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
