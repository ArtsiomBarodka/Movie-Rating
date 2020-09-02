package epam.my.project.controller.servlet;

import epam.my.project.controller.exception.PageException;
import epam.my.project.service.exception.AccessDeniedException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.RetrieveSocialAccountFailedException;
import epam.my.project.service.factory.ServiceConfiguration;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.service.factory.ServiceType;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Abstract servlet.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public abstract class AbstractServlet extends HttpServlet {
    /**
     * The Logger.
     */
    protected final Logger logger = getLogger(getClass());
    /**
     * The Service factory.
     */
    protected ServiceFactory serviceFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceConfiguration.getServiceFactory(ServiceType.FINAL_SERVICE);
        logger.info("Servlet was initialized");
    }

    /**
     * Handle exception.
     *
     * @param e the e
     */
    protected void handleException(Exception e) {
        if(e instanceof ObjectNotFoundException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_NOT_FOUND);
        } else if(e instanceof RetrieveSocialAccountFailedException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_UNAUTHORIZED);
        } else if(e instanceof AccessDeniedException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_FORBIDDEN);
        } else if(e instanceof InternalServerErrorException){
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void destroy() {
        logger.info("Servlet was destroyed");
    }
}
