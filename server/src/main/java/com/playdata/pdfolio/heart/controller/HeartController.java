package com.playdata.pdfolio.heart.controller;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.project.Project;
import com.playdata.pdfolio.domain.reqeuest.heart.HeartRequest;
import com.playdata.pdfolio.heart.service.heart.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/gather") // gather 좋아요 추가
    public void GatherHeartInsert(@RequestBody HeartRequest request) {
        heartService.addGatherHeart(
                Member.builder().id(request.memberId()).build(),
                Gather.builder().id(request.gatherId()).build()
        );
    }

    @PostMapping("/project")// project 좋아요 추가
    public void ProjectHeartInsert(@RequestBody HeartRequest request) {
        heartService.addProjectHeart(
                Member.builder().id(request.memberId()).build(),
                Project.builder().id(request.gatherId()).build()
        );
    }
}
