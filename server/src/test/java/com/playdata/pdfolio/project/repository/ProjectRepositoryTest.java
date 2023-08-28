package com.playdata.pdfolio.project.repository;

import com.playdata.pdfolio.RepositoryTest;
import com.playdata.pdfolio.domain.request.project.ProjectSearchParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest extends RepositoryTest {

    @Test
    void findByCondition() {
        ProjectSearchParameter parameter = ProjectSearchParameter.of("1", "10", "createdAt", "JAVA,SPRING");
        projectRepository.findByCondition(parameter);
    }

}