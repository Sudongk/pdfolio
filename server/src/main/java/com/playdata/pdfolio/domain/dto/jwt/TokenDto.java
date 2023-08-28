package com.playdata.pdfolio.domain.dto.jwt;

import lombok.Builder;

@Builder
public record TokenDto(
        String grantType,
        String accessToken,
        String refreshToken) {
}
