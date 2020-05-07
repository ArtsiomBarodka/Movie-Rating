package epam.my.project.util;

import epam.my.project.configuration.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewUtil {
    public static void forwardToFragment(String jspFragment, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(req, resp);
    }

    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Constants.CURRENT_PAGE, jspPage);
        req.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    public static void redirect(String url, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(url);
    }
}
