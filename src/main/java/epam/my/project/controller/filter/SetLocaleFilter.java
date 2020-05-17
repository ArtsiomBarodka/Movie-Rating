package epam.my.project.controller.filter;

import epam.my.project.configuration.Constants;
import epam.my.project.util.WebUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "SetLocaleFilter")
public class SetLocaleFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(WebUtil.hasLocaleCookie(request)){
            request.setAttribute(Constants.LOCALE, WebUtil.getLocaleCookie(request).toString());
        } else {
            Locale locale = request.getLocale();
            request.setAttribute(Constants.LOCALE, locale.getLanguage());
        }
        chain.doFilter(request, response);
    }
}
