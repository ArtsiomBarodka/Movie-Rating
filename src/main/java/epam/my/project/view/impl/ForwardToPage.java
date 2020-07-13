package epam.my.project.view.impl;

import epam.my.project.configuration.Constants;
import javax.servlet.ServletException;
import java.io.IOException;

public final class ForwardToPage extends AbstractView {

    @Override
    public void render(Object rendering) throws ServletException, IOException {
        request.setAttribute(Constants.CURRENT_PAGE, rendering);
        request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
    }
}
