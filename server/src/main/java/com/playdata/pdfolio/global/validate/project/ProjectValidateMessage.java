package com.playdata.pdfolio.global.validate.project;

import lombok.Getter;

@Getter
public class ProjectValidateMessage {

    public static final String TITLE_NOT_BLANK_MESSAGE = "프로젝트 제목을 작성해주세요.";
    public static final String TITLE_MAX_LENGTH_MESSAGE = "프로젝트 제목은 50자 이하여야 합니다.";

    public static final String CONTENT_NOT_BLANK_MESSAGE = "프로젝트 내용을 작성해주세요.";

    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "프로젝트 요약을 작성해주세요.";
    public static final String DESCRIPTION_MAX_LENGTH_MESSAGE = "프로젝트 요약은 50자 이하여야 합니다.";

    public static final String INVALID_URL_TYPE_MESSAGE = "유효하지 않는 URL 타입입니다.";
    public static final String REPO_URL_NOT_BLANK_MESSAGE = "깃헙 저장소 주소를 입력해주세요.";
    public static final String THUMBNAIL_NOT_BLANK_MESSAGE = "썸네일 이미지를 넣아주세요.";

}
