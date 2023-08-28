package com.playdata.pdfolio.domain.response.oauth2;

import com.playdata.pdfolio.domain.dto.jwt.TokenDto;
import com.playdata.pdfolio.domain.dto.oauth2.LoginInfoDto;

public record Oauth2Response(
        LoginInfoDto loginInfo,
        TokenDto token) {

}
