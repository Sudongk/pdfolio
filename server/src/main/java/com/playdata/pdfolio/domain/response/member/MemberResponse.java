package com.playdata.pdfolio.domain.response.member;

import com.playdata.pdfolio.domain.entity.common.Skill;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.member.MemberSkill;

import java.util.List;
import java.util.stream.Collectors;

public record MemberResponse(
        Long id,
        String name,
        String providerName,
        String nickName,
        String imageUrl,
        List<String> skills
) {

    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getProviderName(),
                member.getNickName(),
                member.getImageUrl(),
                member.getSkills().stream()
                        .map(MemberSkill::getSkill)
                        .map(Skill::getSkillName)
                        .collect(Collectors.toList())
        );
    }
}
