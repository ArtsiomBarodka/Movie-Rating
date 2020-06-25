package epam.my.project.controller.command;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.exception.*;
import epam.my.project.model.validation.ValidatorFactory;
import epam.my.project.service.factory.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public abstract class FrontCommand implements Serializable {
    protected ServiceFactory serviceFactory;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void  init(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory){
        this.serviceFactory = serviceFactory;
        this.request = request;
        this.response = response;
    }

    public abstract void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException, RetrieveSocialAccountFailedException, AccessDeniedException;

    protected SortMode getSortMode(){
        Optional<String> sortMode = Optional.ofNullable(request.getParameter(Constants.SORT));
        if(sortMode.isPresent()){
            Optional<SortMode> sort = Optional.ofNullable(SortMode.of(sortMode.get()));
            return sort.orElse(SortMode.RATING);
        }
        return SortMode.RATING;
    }

    protected int getPageable(){
        Optional<String> pageable = Optional.ofNullable(request.getParameter("pageable"));
        return pageable.map(s -> ValidatorFactory.IS_NUMBER_VALUE.validate(s) ? Integer.parseInt(s) : Constants.ITEMS_PER_HTML_PAGE_1)
                .orElseGet(() -> pageable.map(Integer::parseInt).orElse(Constants.ITEMS_PER_HTML_PAGE_1));
    }

    protected int getPageCount(int totalCount, int maxCountPerPage){
        int result = totalCount / maxCountPerPage;
        if(result * maxCountPerPage != totalCount) {
            result++;
        }
        return result;
    }

    protected void forwardToFragment(String jspFragment) throws ServletException, IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(request, response);
    }

    protected void forwardToPage(String jspPage) throws ServletException, IOException {
        request.setAttribute(Constants.CURRENT_PAGE, jspPage);
        request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
    }

    protected void redirect(String url) throws IOException {
        response.sendRedirect(url);
    }

}
