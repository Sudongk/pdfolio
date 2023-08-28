package com.playdata.pdfolio.domain.reqeuest.heart;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.heart.GatherHeart;
import com.playdata.pdfolio.domain.entity.member.Member;

public record HeartRequest(Long memberId, Long gatherId) {

    public GatherHeart toEntity() {
        return GatherHeart
                .builder()
                .build();
        }
}
