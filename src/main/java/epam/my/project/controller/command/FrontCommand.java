package epam.my.project.controller.command;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.exception.*;
import epam.my.project.model.validation.ValidatorFactory;
import epam.my.project.service.factory.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

/**
 * The type Front command.
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

    /**
     * Get sort mode sort mode.
     *
     * @return the sort mode
     */
    protected SortMode getSortMode(){
        Optional<String> sortMode = Optional.ofNullable(request.getParameter(RequestParameterNames.SORT));
        if(sortMode.isPresent()){
            Optional<SortMode> sort = Optional.ofNullable(SortMode.of(sortMode.get()));
            return sort.orElse(SortMode.RATING);
        }
        return SortMode.RATING;
    }

    /**
     * Get pageable int.
     *
     * @return the int
     */
    protected int getPageable(){
        Optional<String> pageable = Optional.ofNullable(request.getParameter(RequestParameterNames.PAGEABLE));
        return pageable.map(s -> ValidatorFactory.IS_NUMBER_VALUE.validate(s) ? Integer.parseInt(s) : Constants.ITEMS_PER_HTML_PAGE_1)
                .orElseGet(() -> pageable.map(Integer::parseInt).orElse(Constants.ITEMS_PER_HTML_PAGE_1));
    }

    /**
     * Get page count int.
     *
     * @param totalCount      the total count
     * @param maxCountPerPage the max count per page
     * @return the int
     */
    protected int getPageCount(int totalCount, int maxCountPerPage){
        int result = totalCount / maxCountPerPage;
        if(result * maxCountPerPage != totalCount) {
            result++;
        }
        return result;
    }
}
