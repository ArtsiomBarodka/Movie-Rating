package epam.my.project.view.factory;

import epam.my.project.view.impl.*;

public enum  ViewFactory {
    VIEW_FACTORY;

    private ForwardToPage forwardToPage;
    private ForwardToFragment forwardToFragment;
    private ForwardToCommand forwardToCommand;
    private Redirect redirect;
    private SendJSON sendJSON;

    ViewFactory(){
        init();
    }

    public ForwardToPage getForwardToPage() {
        return forwardToPage;
    }

    public ForwardToFragment getForwardToFragment() {
        return forwardToFragment;
    }

    public ForwardToCommand getForwardToCommand() {
        return forwardToCommand;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public SendJSON getSendJSON() {
        return sendJSON;
    }

    private void init() {
        this.forwardToPage = new ForwardToPage();
        this.forwardToFragment = new ForwardToFragment();
        this.forwardToCommand = new ForwardToCommand();
        this.redirect = new Redirect();
        this.sendJSON = new SendJSON();
    }
}
