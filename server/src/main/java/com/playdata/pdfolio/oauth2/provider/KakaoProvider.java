package com.playdata.pdfolio.oauth2.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class KakaoProvider implements Oauth2Provider{

    private final String name = "kakao";

    @Value("${oauth2.user.kakao.client-id}")
    private String clientId;
    @Value("${oauth2.user.kakao.client-secret}")
    private String clientSecret;
    @Value("${oauth2.user.kakao.redirect-uri}")
    private String redirectUri;
    @Value("${oauth2.user.kakao.access-token-uri}")
    private String accessTokenUri;
    @Value("${oauth2.user.kakao.user-info-uri}")
    private String userInfoUri;
}
