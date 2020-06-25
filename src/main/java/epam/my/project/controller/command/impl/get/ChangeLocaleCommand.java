package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.WebUtil;
import java.io.IOException;
import java.util.Optional;

public class ChangeLocaleCommand extends FrontCommand {
    private static final long serialVersionUID = -3809362932565779661L;

    @Override
    public void execute() throws IOException {
        Optional<String> locale = Optional.ofNullable(request.getParameter("locale"));
        Optional<String> returnUrl = Optional.ofNullable(request.getParameter("return"));
        if (locale.isPresent() && returnUrl.isPresent()){
            WebUtil.setLocale(request, locale.get());
            redirect(returnUrl.get());
        }
        redirect("/app/movies");
    }
}
