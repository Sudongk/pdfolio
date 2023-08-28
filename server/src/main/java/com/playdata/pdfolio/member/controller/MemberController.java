package com.playdata.pdfolio.member.controller;

import com.playdata.pdfolio.auth.UserInfo;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.request.member.UpdateRequest;
import com.playdata.pdfolio.domain.request.project.ProjectCreateRequest;
import com.playdata.pdfolio.domain.response.member.MemberResponse;
import com.playdata.pdfolio.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@AuthenticationPrincipal UserInfo userInfo){
        memberService.withdraw(userInfo.getMemberId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse me(@AuthenticationPrincipal UserInfo userInfo){
        Member member = memberService.findByIdFetchMemberSkill(userInfo.getMemberId());
        return MemberResponse.from(member);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBasic(
            @AuthenticationPrincipal UserInfo userInfo,
            @RequestBody UpdateRequest updateRequest){
        memberService.updateBasic(userInfo.getMemberId(), updateRequest);
    }

    @PutMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateContainSkills(
            @AuthenticationPrincipal UserInfo userInfo,
            @RequestBody UpdateRequest updateRequest)
    {
        memberService.updateContainSkills(
                userInfo.getMemberId(),
                updateRequest);
    }

}
