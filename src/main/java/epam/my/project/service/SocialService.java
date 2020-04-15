package epam.my.project.service;

import epam.my.project.model.SocialAccount;

public interface SocialService {
    String getAuthorizeUrl();

    SocialAccount getSocialAccount(String authToken);
}
