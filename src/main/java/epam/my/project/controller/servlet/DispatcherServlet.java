package epam.my.project.controller.servlet;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.controller.request.RequestHandler;
import epam.my.project.controller.request.RequestHandlerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Dispatcher servlet.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@WebServlet("/app/*")
public class DispatcherServlet extends AbstractServlet {
    private RequestHandler requestHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        requestHandler = RequestHandlerFactory.INSTANCE.getRequestHandler();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if(!isMediaRequest(req) || !isStaticRequest(req)){
                FrontCommand command = requestHandler.getCommand(req);
                command.init(serviceFactory, req, resp);
                command.execute();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private boolean isMediaRequest(HttpServletRequest request){
        String uri = request.getRequestURI();
        return uri.contains("media");
    }

    private boolean isStaticRequest(HttpServletRequest request){
        String uri = request.getRequestURI();
        return uri.contains("static");
    }

}
