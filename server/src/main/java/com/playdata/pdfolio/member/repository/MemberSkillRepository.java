package com.playdata.pdfolio.member.repository;

import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.member.MemberSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberSkillRepository extends JpaRepository<MemberSkill, Long> {

    List<MemberSkill> findByMember(Member member);
    void deleteByMember(Member member);

}
