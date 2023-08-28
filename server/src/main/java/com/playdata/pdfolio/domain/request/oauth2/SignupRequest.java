package com.playdata.pdfolio.domain.request.oauth2;

import java.util.List;

public record SignupRequest(
        String accessToken,
        String nickName,
        String imageUrl,
        List<String> skills
) {
}
