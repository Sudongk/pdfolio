package com.playdata.pdfolio.oauth2.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class GoogleProvider implements Oauth2Provider {

    private final String name = "google";

    @Value("${oauth2.user.google.client-id}")
    private String clientId;
    @Value("${oauth2.user.google.client-secret}")
    private String clientSecret;
    @Value("${oauth2.user.google.redirect-uri}")
    private String redirectUri;
    @Value("${oauth2.user.google.access-token-uri}")
    private String accessTokenUri;
    @Value("${oauth2.user.google.user-info-uri}")
    private String userInfoUri;
}
