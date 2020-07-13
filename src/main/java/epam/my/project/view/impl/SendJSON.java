package epam.my.project.view.impl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Writer;

public class SendJSON extends AbstractView {
    @Override
    public void render(Object rendering) throws ServletException, IOException {
        String content = rendering.toString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (Writer wr = response.getWriter()) {
            wr.write(content);
            wr.flush();
        }
    }
}
