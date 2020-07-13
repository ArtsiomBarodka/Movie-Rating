package epam.my.project.view.impl;

import javax.servlet.ServletException;
import java.io.IOException;

public class ForwardToFragment extends AbstractView {
    @Override
    public void render(Object rendering) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + rendering).forward(request, response);
    }
}
