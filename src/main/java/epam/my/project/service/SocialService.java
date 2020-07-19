package epam.my.project.service;

import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.SocialAccount;

/**
 * The interface Social service.
 */
public interface SocialService {
    /**
     * Get authorize url string.
     *
     * @return the string
     */
    default String getAuthorizeUrl(){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets social account.
     *
     * @param verificationCode the verification code
     * @return the social account
     * @throws RetrieveSocialAccountFailedException the retrieve social account failed exception
     */
    SocialAccount getSocialAccount(String verificationCode) throws RetrieveSocialAccountFailedException;
}
