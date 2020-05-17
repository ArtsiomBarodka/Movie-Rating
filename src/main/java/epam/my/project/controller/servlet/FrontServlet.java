package epam.my.project.controller.servlet;

import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.*;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet("/*")
public class FrontServlet extends HttpServlet {
    private static final Logger logger = getLogger(FrontServlet.class);
    private ServiceFactory serviceFactory;

    @Override
    public void init() throws ServletException {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp){
        try {
            FrontCommand command = getCommand(req);
            command.init(req, resp, serviceFactory);
            command.execute();
        } catch (ObjectNotFoundException e) {
            //
        } catch (RetrieveSocialAccountFailedException e) {
            ///
        } catch (AccessDeniedException e) {
            ////
        } catch (InternalServerErrorException e) {
            ////
        } catch (IOException | ServletException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        return null;
    }

}
