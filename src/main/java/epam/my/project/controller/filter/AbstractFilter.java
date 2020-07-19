package epam.my.project.controller.filter;

import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Abstract filter.
 */
public abstract class AbstractFilter implements Filter {
    /**
     * The Logger.
     */
    protected final Logger logger = getLogger(getClass());
    /**
     * The Service factory.
     */
    protected ServiceFactory serviceFactory;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
        logger.info("Filter was initialized");
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        doFilter(req, resp, chain);
    }

    /**
     * Do filter.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {
        logger.info("Filter was destroyed");
    }
}
