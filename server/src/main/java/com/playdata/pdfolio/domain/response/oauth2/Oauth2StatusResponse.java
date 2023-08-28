package com.playdata.pdfolio.domain.response.oauth2;

public record Oauth2StatusResponse(
        Boolean isNewMember,
        String providerName,
        String accessToken) {
}
