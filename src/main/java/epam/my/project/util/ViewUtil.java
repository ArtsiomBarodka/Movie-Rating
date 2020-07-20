package epam.my.project.util;

import epam.my.project.controller.request.RequestAttributeNames;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * The type View util.
 */
public final class ViewUtil {
    /**
     * Redirect.
     *
     * @param url      the url
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }

    /**
     * Forward to page.
     *
     * @param page     the page
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(RequestAttributeNames.CURRENT_PAGE, page);
        request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
    }

    /**
     * Forward to fragment.
     *
     * @param fragment the fragment
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public static void forwardToFragment(String fragment, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + fragment).forward(request, response);
    }

    /**
     * Forward to servlet.
     *
     * @param url      the url
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public static void forwardToServlet(String url, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(url).forward(request,response);
    }

    /**
     * Send json.
     *
     * @param json     the json
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public static void sendJSON(Object json, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String content = json.toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (Writer wr = response.getWriter()) {
            wr.write(content);
            wr.flush();
        }
    }

    private ViewUtil(){

    }
}
