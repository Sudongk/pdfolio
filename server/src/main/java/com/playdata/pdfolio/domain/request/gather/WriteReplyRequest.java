package com.playdata.pdfolio.domain.request.gather;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.gather.GatherComment;
import com.playdata.pdfolio.domain.entity.gather.GatherReply;
import com.playdata.pdfolio.domain.entity.member.Member;

public record WriteReplyRequest(
        Long commentId,
        String content
) {
    public GatherReply toEntity(Long memberId){

        return GatherReply.builder()
                .member(Member.builder().id(memberId).build())
                .comment(GatherComment.builder().id(commentId).build())
                .content(content)
                .build();
    }
}
