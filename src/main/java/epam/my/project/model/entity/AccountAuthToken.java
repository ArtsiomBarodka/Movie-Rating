package epam.my.project.model.entity;

public class AccountAuthToken extends AbstractEntity<Long> {
    private static final long serialVersionUID = -7114037316076191889L;

    private String selector;
    private String validator;
    private int AccountId;

    public AccountAuthToken() {
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    @Override
    public String toString() {
        return "AccountAuthToken{" +
                "selector='" + selector + '\'' +
                ", validator='" + validator + '\'' +
                ", AccountId=" + AccountId +
                ", id=" + id +
                '}';
    }
}
