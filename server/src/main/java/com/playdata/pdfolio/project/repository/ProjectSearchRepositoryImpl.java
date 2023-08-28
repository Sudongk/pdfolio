package com.playdata.pdfolio.project.repository;

import com.playdata.pdfolio.domain.entity.common.Skill;
import com.playdata.pdfolio.domain.request.project.ProjectSearchParameter;
import com.playdata.pdfolio.domain.response.project.ProjectResponse;
import com.playdata.pdfolio.domain.response.project.ProjectSkillResponse;
import com.playdata.pdfolio.domain.response.project.TempProjectResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

import static com.playdata.pdfolio.domain.entity.project.QProject.*;
import static com.playdata.pdfolio.domain.entity.project.QProjectSkill.*;

@RequiredArgsConstructor
public class ProjectSearchRepositoryImpl implements ProjectSearchRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProjectResponse> findByCondition(ProjectSearchParameter searchParameter) {
        queryFactory
                .select(Projections.constructor(TempProjectResponse.class,
                        project.id,
                        project.title,
                        project.description,
                        project.heartCount,
                        project.viewCount,
                        project.thumbNailUrl.url,
                        project.createdAt,
                        Projections.constructor(ProjectSkillResponse.class,
                                projectSkill.skill
                        )
                ))
                .from(project)
                .leftJoin(project.skills, projectSkill)
                .where(project.id.in(
                        JPAExpressions.select(projectSkill.project.id)
                                .from(projectSkill)
                                .where(projectSkillsIn(searchParameter.getSkillCategory().getSkills()))
                ))
                .groupBy(project.id, project.skills)
                .orderBy(project.createdAt.desc())
                .fetch();

        return null;
    }

    private BooleanExpression projectSkillsIn(List<Skill> Skills) {
        if (Skills.isEmpty()) {
            return null;
        }

        return projectSkill.skill.in(Skills);
    }
}
