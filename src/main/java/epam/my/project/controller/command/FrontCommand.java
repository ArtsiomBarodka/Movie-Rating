package epam.my.project.controller.command;

import epam.my.project.service.exception.AccessDeniedException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.RetrieveSocialAccountFailedException;
import epam.my.project.service.factory.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * The type Front command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public abstract class FrontCommand implements Serializable {
    /**
     * The Service factory.
     */
    protected ServiceFactory serviceFactory;
    /**
     * The Request.
     */
    protected HttpServletRequest request;
    /**
     * The Response.
     */
    protected HttpServletResponse response;

    /**
     * Init.
     *
     * @param serviceFactory the service factory
     * @param request        the request
     * @param response       the response
     */
    public void init(ServiceFactory serviceFactory,
                     HttpServletRequest request,
                     HttpServletResponse response){
        this.serviceFactory = serviceFactory;
        this.request = request;
        this.response = response;
    }

    /**
     * Execute.
     *
     * @throws IOException                          the io exception
     * @throws ServletException                     the servlet exception
     * @throws InternalServerErrorException         the internal server error exception
     * @throws ObjectNotFoundException              the object not found exception
     * @throws RetrieveSocialAccountFailedException the retrieve social account failed exception
     * @throws AccessDeniedException                the access denied exception
     */
    public abstract void execute() throws IOException, ServletException, InternalServerErrorException,
            ObjectNotFoundException, RetrieveSocialAccountFailedException, AccessDeniedException;
}
