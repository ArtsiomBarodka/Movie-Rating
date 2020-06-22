package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.WebUtil;
import java.io.IOException;

public class ChangeLocaleCommand extends FrontCommand {
    private static final long serialVersionUID = -3809362932565779661L;

    @Override
    public void execute() throws IOException {
        String locale = request.getParameter("locale");
        String returnUrl = request.getParameter("return");
        WebUtil.setLocale(request, locale);
        redirect(returnUrl);
    }
}
