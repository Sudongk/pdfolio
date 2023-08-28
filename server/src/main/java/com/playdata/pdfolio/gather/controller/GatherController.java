package com.playdata.pdfolio.gather.controller;


import com.playdata.pdfolio.auth.UserInfo;
import com.playdata.pdfolio.domain.dto.gather.SearchDto;
import com.playdata.pdfolio.domain.request.gather.WriteCommentRequest;
import com.playdata.pdfolio.domain.request.gather.WriteReplyRequest;
import com.playdata.pdfolio.domain.request.gather.WriteRequest;
import com.playdata.pdfolio.domain.response.gather.GatherDetailResponse;
import com.playdata.pdfolio.domain.response.gather.GatherResponse;
import com.playdata.pdfolio.gather.service.GatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gather")
public class GatherController {
    private final GatherService gatherService;

    // 모집글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void GatherWrite(
            @AuthenticationPrincipal UserInfo userInfo,
            @RequestBody WriteRequest writeRequest){
        gatherService.writeGather(writeRequest, userInfo.getMemberId());
    }
    // 모집글 수정
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void GatherModify(@PathVariable(name = "id") Long id,
                             @AuthenticationPrincipal UserInfo userInfo,
                             @RequestBody WriteRequest writeRequest)
    {
        gatherService.modifyGather(writeRequest, id, userInfo.getMemberId());
    }
    // 모집글 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void GatherDelete(@PathVariable(name = "id") Long id){
        gatherService.deleteGather(id);
    }

    // 모집글 상세 보기
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GatherDetailResponse detailGather(@PathVariable(name = "id") Long id){
       return gatherService.detailGather(id);
    }

    // 모집글 전체 보기 / 모집글 제목 , 글 내용 , 카테고리 , 스킬 검색
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<GatherResponse> allGather(
            @RequestParam(required = false,defaultValue = "0",name = "page")
            Integer page,
            @RequestParam(required = false,defaultValue = "8",name = "size")
            Integer size,
//            @RequestParam(required = false,defaultValue = "",name = "keyword")
//            String keyword,
            SearchDto searchDto
            ) {
        PageRequest request = PageRequest.of(page, size);
//        return gatherService.allGather(request, keyword, category);
        return gatherService.allGather(request, searchDto);
    }



// -----------------------------------------------------------------------------
    // 코멘트 작성
    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void GatherCommentWrite(
            @RequestBody WriteCommentRequest writeCommentRequest,
            @AuthenticationPrincipal UserInfo userInfo
    ){
        gatherService.writeGatherComment(writeCommentRequest,userInfo.getMemberId());
    }
    // 코멘트 수정
    @PutMapping("/comment/{id}")
    public void GatherCommentModify(
                            @PathVariable(name = "id") Long id,
                            @RequestBody WriteCommentRequest writeCommentRequest)
    {
        gatherService.modifyGatherComment(writeCommentRequest, id);
    }
    // 코멘트 삭제
    @DeleteMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void GatherCommentDelete(@PathVariable(name = "id") Long id){
        gatherService.deleteGatherComment(id);
    }
// -----------------------------------------------------------------------------
    // 리플라이 작성
    @PostMapping("/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public void GatherReplyWrite(
            @RequestBody WriteReplyRequest writeReplyRequest,
            @AuthenticationPrincipal UserInfo userInfo
    ){
        gatherService.writeGatherReply(writeReplyRequest,userInfo.getMemberId());
    }

    // 리플라이 수정
    @PutMapping("/reply/{id}")
    public void GatherReplyModify(@PathVariable(name = "id") Long id,
                             @RequestBody WriteReplyRequest writeReplyRequest)
    {
        gatherService.modifyGatherReply(writeReplyRequest, id);
    }
    // 리플라이 삭제
    @DeleteMapping("/reply/{id}")
    public void GatherReplyDelete(@PathVariable(name = "id") Long id){
        gatherService.deleteGatherReply(id);
    }
}
