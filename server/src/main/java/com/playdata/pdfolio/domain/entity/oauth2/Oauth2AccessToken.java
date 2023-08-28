package com.playdata.pdfolio.domain.entity.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Oauth2AccessToken(
        @JsonProperty("access_token") String token,
        @JsonProperty("token_type") String type,
        String scope) {
}
