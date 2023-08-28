package com.playdata.pdfolio.oauth2.provider;

public interface Oauth2Provider {
    String getName();
    String getClientId();
    String getClientSecret();
    String getRedirectUri();
    String getAccessTokenUri();
    String getUserInfoUri();

}
