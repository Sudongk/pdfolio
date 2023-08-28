package com.playdata.pdfolio.domain.entity.project;

import com.playdata.pdfolio.domain.entity.common.Skill;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(
        indexes = {
                @Index(name = "idx_project_skill_skill", columnList = "skill")
        }
)
public class ProjectSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Skill skill;

    public String getSkillName() {
        return this.skill.getSkillName();
    }

}
