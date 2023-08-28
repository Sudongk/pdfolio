package com.playdata.pdfolio.domain.response.auth;

import com.playdata.pdfolio.domain.dto.jwt.UserInfoDto;

public record AuthResponse(
        UserInfoDto user
) {
}
