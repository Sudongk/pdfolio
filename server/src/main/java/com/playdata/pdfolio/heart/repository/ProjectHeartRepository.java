package com.playdata.pdfolio.heart.repository;

import com.playdata.pdfolio.domain.entity.heart.ProjectHeart;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface ProjectHeartRepository extends JpaRepository<ProjectHeart,Long> {
    Optional<ProjectHeart> findByMemberAndProject(Member member, Project project);
}
