    package com.playdata.pdfolio.domain.response.gather;

    import com.playdata.pdfolio.domain.dto.gather.CommentDto;
    import com.playdata.pdfolio.domain.dto.gather.GatherSkillDto;
    import com.playdata.pdfolio.domain.entity.gather.*;
    import com.playdata.pdfolio.domain.entity.member.Member;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Set;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter

    public class GatherDetailResponse {
        private Long id;
        private String title;
        private String content;
        private LocalDate startDate;
        private LocalDate closeDate;
        private Long teamSize;
        private String category;
        private String contact;
        private Integer heartCount;
        private Integer viewCount;
        private Boolean isDeleted;

        private Long memberId;
        private String memberName;
        private String memberImageUrl;

        private List<GatherSkillDto> skills;

        private List<CommentDto> gatherCommentList;
//        private List<ReplyDto> gatherReplies;

        public GatherDetailResponse(Gather gather) {
            this.id = gather.getId();
            this.title = gather.getTitle();
            this.content = gather.getContent();
            this.startDate = gather.getStartDate();
            this.closeDate = gather.getCloseDate();
            this.teamSize = gather.getTeamSize();
            this.category = gather.getCategory().name();
            this.contact = gather.getContact();
            this.heartCount = gather.getHeartCount();
            this.viewCount = gather.getViewCount();
            this.memberId = gather.getMember().getId();
            this.memberName = gather.getMember().getNickName();
            this.memberImageUrl = gather.getMember().getImageUrl();
            this.skills = gather.getSkills().stream().map(GatherSkillDto::new).toList();

            this.gatherCommentList = gather.getComments() != null?
                    gather.getComments()
                            .stream()
                            .map(CommentDto::new)
                            .toList()
                    :new ArrayList<>();

        }


    }
