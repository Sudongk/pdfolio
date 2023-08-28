package com.playdata.pdfolio.domain.request.auth;

public record TokenRenewRequest(
        String accessToken,
        String refreshToken) {
}
