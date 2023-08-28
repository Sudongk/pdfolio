package com.playdata.pdfolio.domain.response.project;

import com.playdata.pdfolio.domain.entity.project.ProjectReply;
import com.playdata.pdfolio.domain.response.member.MemberInfoResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectReplyResponse {

    private Long replyId;
    private String content;
    private String createdAt;
    private MemberInfoResponse author;

    public static ProjectReplyResponse of(final ProjectReply projectReply) {
        return new ProjectReplyResponse(
                projectReply.getId(),
                projectReply.getContent(),
                projectReply.getCreatedAt().toString(),
                MemberInfoResponse.of(projectReply.getMember())
        );
    }
}
