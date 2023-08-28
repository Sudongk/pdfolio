package com.playdata.pdfolio.domain.response.project;

import com.playdata.pdfolio.domain.entity.common.Skill;
import com.playdata.pdfolio.domain.entity.project.ProjectSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ProjectSkillResponse {

    private String skillName;

    public ProjectSkillResponse(Skill skill) {
        this.skillName = skill.getSkillName();
    }

    public static ProjectSkillResponse of(ProjectSkill projectSkill) {
        return new ProjectSkillResponse(projectSkill.getSkill());
    }

    public static List<ProjectSkillResponse> of(List<ProjectSkill> projectSkills) {
        return projectSkills.stream()
                .map(ProjectSkillResponse::of)
                .toList();
    }
}
