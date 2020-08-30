package epam.my.project.controller.filter;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.exception.PageException;
import epam.my.project.util.ViewUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Error filter.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
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
            request.setAttribute(Constants.EXCEPTION_CODE, e.getCode());
            ViewUtil.forwardToPage("page/error.jsp",request,response);
        } catch (Exception e){
            logger.error(e.getMessage());
            ViewUtil.forwardToPage("page/error.jsp",request,response);
        }
    }
}
