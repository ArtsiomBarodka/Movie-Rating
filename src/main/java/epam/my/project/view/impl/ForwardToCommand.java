package epam.my.project.view.impl;

import javax.servlet.ServletException;
import java.io.IOException;

public class ForwardToCommand extends AbstractView {
    @Override
    public void render(Object rendering) throws ServletException, IOException {
        request.getRequestDispatcher(rendering.toString()).forward(request,response);
    }
}
