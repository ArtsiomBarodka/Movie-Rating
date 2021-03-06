package com.epam.mrating.controller.filter;

import com.epam.mrating.util.WebUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * The type Set locale filter.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@WebFilter(filterName = "SetLocaleFilter")
public class SetLocaleFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!WebUtil.isSessionLocaleCreated(request)){
            Locale locale = request.getLocale();
            WebUtil.setSessionLocale(request, locale.getLanguage());
        }
        chain.doFilter(request, response);
    }
}
