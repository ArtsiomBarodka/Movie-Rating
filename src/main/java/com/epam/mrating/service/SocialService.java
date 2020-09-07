package com.epam.mrating.service;

import com.epam.mrating.model.domain.SocialAccount;
import com.epam.mrating.service.exception.RetrieveSocialAccountFailedException;

/**
 * The interface Social service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
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
