package epam.my.project.controller.filter;

import epam.my.project.configuration.Constants;
import epam.my.project.exception.PageException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorFilter")
public class ErrorFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request,response);
        } catch (PageException e){
            if(e.getCode() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR){
                logger.error(e.getMessage());
            } else {
                logger.warn(e.getMessage());
            }
            request.setAttribute(Constants.CODE, e.getCode());
            request.setAttribute(Constants.CURRENT_PAGE, "page/error.jsp");
            request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
        }
    }
}
