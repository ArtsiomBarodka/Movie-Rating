package epam.my.project.service;

import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.SocialAccount;

public interface SocialService {
    default String getAuthorizeUrl(){
        throw new UnsupportedOperationException();
    }

    SocialAccount getSocialAccount(String verificationCode) throws RetrieveSocialAccountFailedException;
}
