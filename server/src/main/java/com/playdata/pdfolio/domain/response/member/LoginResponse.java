package com.playdata.pdfolio.domain.response.member;

import com.playdata.pdfolio.domain.dto.jwt.TokenDto;

public record LoginResponse(TokenDto jwtTokenDto) {
}
