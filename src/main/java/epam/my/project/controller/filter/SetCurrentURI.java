package epam.my.project.controller.filter;

import epam.my.project.configuration.Constants;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebFilter(filterName = "SetCurrentURI")
public class SetCurrentURI extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        Optional<String> query = Optional.ofNullable(request.getQueryString());
        if (query.isPresent()) {
            String queryDecode = URLEncoder.encode(query.get(), StandardCharsets.UTF_8.toString());
            request.setAttribute(Constants.CURRENT_URI, uri.concat("?").concat(queryDecode));
        } else {
            request.setAttribute(Constants.CURRENT_URI, uri);
        }
        chain.doFilter(request, response);
    }
}
