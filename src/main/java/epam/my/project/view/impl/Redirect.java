package epam.my.project.view.impl;

import javax.servlet.ServletException;
import java.io.IOException;

public class Redirect extends AbstractView {
    @Override
    public void render(Object rendering) throws ServletException, IOException {
        response.sendRedirect(rendering.toString());
    }

}
