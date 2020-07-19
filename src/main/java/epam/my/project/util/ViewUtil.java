package epam.my.project.util;

import epam.my.project.configuration.Constants;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public final class ViewUtil {
    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }

    public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(Constants.CURRENT_PAGE, page);
        request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
    }

    public static void forwardToFragment(String fragment, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + fragment).forward(request, response);
    }

    public static void forwardToServlet(String url, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(url).forward(request,response);
    }

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
