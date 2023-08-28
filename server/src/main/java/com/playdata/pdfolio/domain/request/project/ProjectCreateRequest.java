package com.playdata.pdfolio.domain.request.project;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

import static com.playdata.pdfolio.global.validate.project.ProjectValidateMessage.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectCreateRequest {

    @NotBlank(message = TITLE_NOT_BLANK_MESSAGE)
    @Length(max = 50, message = TITLE_MAX_LENGTH_MESSAGE)
    private String title;

    @NotBlank(message = CONTENT_NOT_BLANK_MESSAGE)
    private String content;

    @NotBlank(message = DESCRIPTION_NOT_BLANK_MESSAGE)
    @Length(max = 50, message = DESCRIPTION_MAX_LENGTH_MESSAGE)
    private String description;

    @URL(message = INVALID_URL_TYPE_MESSAGE)
    @NotBlank(message = REPO_URL_NOT_BLANK_MESSAGE)
    private String repositoryUrl;

    @URL(message = INVALID_URL_TYPE_MESSAGE)
    private String publishUrl;

    @URL(message = INVALID_URL_TYPE_MESSAGE)
    @NotBlank(message = THUMBNAIL_NOT_BLANK_MESSAGE)
    private String thumbNailUrl;

    private List<String> projectSkills;
}
