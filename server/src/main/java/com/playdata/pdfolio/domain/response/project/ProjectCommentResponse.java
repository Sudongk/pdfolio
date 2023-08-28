package com.playdata.pdfolio.domain.response.project;

import com.playdata.pdfolio.domain.entity.project.ProjectComment;
import com.playdata.pdfolio.domain.response.member.MemberInfoResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectCommentResponse {

    private Long commentId;
    private String content;
    private String createdAt;
    private MemberInfoResponse author;
    List<ProjectReplyResponse> replies;

    public static ProjectCommentResponse of(final ProjectComment projectComment) {
        return new ProjectCommentResponse(
                projectComment.getId(),
                projectComment.getContent(),
                projectComment.getCreatedAt().toString(),
                MemberInfoResponse.of(projectComment.getMember()),
                projectComment.getReplies()
                        .stream()
                        .map(ProjectReplyResponse::of)
                        .toList()
        );
    }
}
