package com.playdata.pdfolio.project.repository;

import com.playdata.pdfolio.domain.request.project.ProjectSearchParameter;
import com.playdata.pdfolio.domain.response.project.ProjectResponse;
import org.springframework.data.domain.Page;

public interface ProjectSearchRepository {
    Page<ProjectResponse> findByCondition(ProjectSearchParameter searchParameter);
}
