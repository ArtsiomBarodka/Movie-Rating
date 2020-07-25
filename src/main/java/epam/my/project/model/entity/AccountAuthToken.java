package epam.my.project.model.entity;

/**
 * The type Account auth token.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class AccountAuthToken extends AbstractEntity<Long> {
    private static final long serialVersionUID = -7114037316076191889L;

    private String selector;
    private String validator;
    private int accountId;

    /**
     * Instantiates a new Account auth token.
     */
    public AccountAuthToken() {
    }

    /**
     * Gets selector.
     *
     * @return the selector
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Sets selector.
     *
     * @param selector the selector
     */
    public void setSelector(String selector) {
        this.selector = selector;
    }

    /**
     * Gets validator.
     *
     * @return the validator
     */
    public String getValidator() {
        return validator;
    }

    /**
     * Sets validator.
     *
     * @param validator the validator
     */
    public void setValidator(String validator) {
        this.validator = validator;
    }

    /**
     * Gets account id.
     *
     * @return the account id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Sets account id.
     *
     * @param accountId the account id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AccountAuthToken{" +
                "selector='" + selector + '\'' +
                ", validator='" + validator + '\'' +
                ", accountId=" + accountId +
                ", id=" + id +
                '}';
    }
}
