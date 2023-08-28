package com.playdata.pdfolio.domain.request.gather;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.gather.GatherCategory;
import com.playdata.pdfolio.domain.entity.member.Member;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;

public record WriteRequest(
        String title,
        String content,
        LocalDate startDate,
        LocalDate closeDate,
        Long teamSize,
        @Enumerated(EnumType.STRING)
        GatherCategory category,
        String contact,
        List<String> skills
) {
    public Gather toEntity(Long memberId){
        return Gather.builder()
                .member(Member.builder().id(memberId).build())
                .title(title)
                .content(content)
                .teamSize(teamSize)
                .startDate(startDate)
                .closeDate(closeDate)
                .category(category)
                .contact(contact)
                .build();
    }
}