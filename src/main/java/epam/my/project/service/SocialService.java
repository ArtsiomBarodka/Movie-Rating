package epam.my.project.service;

import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.SocialAccount;

public interface SocialService {
    String getAuthorizeUrl();

    SocialAccount getSocialAccount(String verificationCode) throws RetrieveSocialAccountFailedException;
}
