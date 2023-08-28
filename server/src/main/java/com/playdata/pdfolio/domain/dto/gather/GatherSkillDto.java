package com.playdata.pdfolio.domain.dto.gather;

import com.playdata.pdfolio.domain.entity.gather.GatherSkill;

public class GatherSkillDto{
    private Long id;
    private String skill;

    public Long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public GatherSkillDto(GatherSkill gatherSkill) {
        this.id = gatherSkill.getId();
        this.skill = gatherSkill.getSkillName();
    }
}
