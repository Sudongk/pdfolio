package com.playdata.pdfolio.heart.service.heart;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.heart.GatherHeart;
import com.playdata.pdfolio.domain.entity.heart.ProjectHeart;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.project.Project;
import com.playdata.pdfolio.gather.repository.GatherRepository;
import com.playdata.pdfolio.heart.repository.GatherHeartRepository;
import com.playdata.pdfolio.heart.repository.ProjectHeartRepository;
import com.playdata.pdfolio.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HeartService {

    private final GatherHeartRepository gatherHeartRepository;
    private final ProjectHeartRepository projectHeartRepository;
    private final GatherRepository gatherRepository;
    private final ProjectRepository projectRepository;

    public void addGatherHeart(Member member, Gather gather){
        Optional<GatherHeart> byMemberAndGather = gatherHeartRepository.findByMemberAndGather(member, gather);
        Gather findGather = gatherRepository.findById(gather.getId())
                .orElseThrow(NoSuchElementException::new);
        if(byMemberAndGather.isPresent()) {
            GatherHeart gatherHeart = byMemberAndGather.get();
            gatherHeartRepository.delete(gatherHeart);
            findGather.decreaseHeartCount();
        }else {
            GatherHeart gatherHeart = new GatherHeart(null, member,gather);
            gatherHeartRepository.save(gatherHeart);
            findGather.increaseHeartCount();
        }
    }
    public void addProjectHeart(Member member, Project project){
        Optional<ProjectHeart> byMemberAndProject = projectHeartRepository.findByMemberAndProject(member, project);
        Project findProject = projectRepository.findById(project.getId())
                .orElseThrow(NoSuchElementException::new);
        if(byMemberAndProject.isPresent()) {
            ProjectHeart projectHeart = byMemberAndProject.get();
            projectHeartRepository.delete(projectHeart);
            findProject.decreaseHeartCount();
        }else{
            ProjectHeart projectHeart = new ProjectHeart(null, member, project);
            projectHeartRepository.save(projectHeart);
            findProject.increaseHeartCount();
        }
    }
}
