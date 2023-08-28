package com.playdata.pdfolio.heart.repository;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.heart.GatherHeart;
import com.playdata.pdfolio.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface GatherHeartRepository extends JpaRepository<GatherHeart, Long> {
    // member와 gather를 받아 해당 게시물에 해당 회원의 좋아요 기록 확인
    Optional<GatherHeart> findByMemberAndGather(Member member, Gather gather);
}
