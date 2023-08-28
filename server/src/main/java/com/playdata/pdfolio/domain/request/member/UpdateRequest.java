package com.playdata.pdfolio.domain.request.member;

import java.util.List;

public record UpdateRequest(
        String nickName,
        String imageUrl,
        List<String> skills) {
}
