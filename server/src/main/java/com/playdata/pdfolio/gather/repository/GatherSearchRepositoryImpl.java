package com.playdata.pdfolio.gather.repository;

import com.playdata.pdfolio.domain.dto.gather.SearchDto;
import com.playdata.pdfolio.domain.entity.common.Skill;
import com.playdata.pdfolio.domain.entity.gather.*;
import com.playdata.pdfolio.domain.response.gather.GatherResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.playdata.pdfolio.domain.entity.gather.QGather.gather;
import static com.playdata.pdfolio.domain.entity.gather.QGatherSkill.*;
import static com.playdata.pdfolio.domain.entity.member.QMember.member;
import static com.playdata.pdfolio.domain.entity.gather.QGatherComment.gatherComment;
import static com.playdata.pdfolio.domain.entity.gather.QGatherReply.gatherReply;

public class GatherSearchRepositoryImpl implements GatherSearchRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GatherResponse> findAllByCondition(
            PageRequest request,
            SearchDto condition
    ){

        QGatherSkill qGatherSkill = gatherSkill;

        List<Long> gatherIdsWithSkills = queryFactory
                .select(qGatherSkill.gather.id)
                .from(qGatherSkill)
                .where(skillEqualString(condition.getSkills()))
                .fetch();

        BooleanExpression isDeletedExpression = gather.isDeleted.eq(false);
        JPAQuery<Gather> query = queryFactory
                .select(gather)
                .from(gather)
                .leftJoin(gather.member, member)
                .leftJoin(gather.skills, gatherSkill)
                .fetchJoin()
                .where(
                        isDeletedExpression
                        ,(categoryEqual(condition.getCategory()))
                        ,(keywordContains(condition.getKeyword()))
                        ,skillEqual(gatherIdsWithSkills,condition.getSkills())
                )
                .offset(request.getPageNumber())
                .limit(request.getPageSize());

        List<Gather> gathers = query.fetch();
        List<GatherResponse> gatherResponses = GatherResponse.of(gathers);

        Long totalSize = queryFactory
                .select(gather.count())
                .from(gather)
                .where(
                        isDeletedExpression
                        ,(categoryEqual(condition.getCategory()))
                        ,(keywordContains(condition.getKeyword()))
                        ,skillEqual(gatherIdsWithSkills,condition.getSkills())
                )
                .fetchOne();

        return new PageImpl<>(gatherResponses, request, totalSize);
    }

    @Override
    public Gather findByIdIncludingUndeletedComments(Long id) {
        BooleanExpression isCommentDeletedFalse = gatherComment.isDeleted.isFalse();
        BooleanExpression isReplyDeletedFalse = gatherReply.isDeleted.isFalse();
        return queryFactory
                .selectFrom(gather)
                .distinct()
                .leftJoin(gather.skills)
                .leftJoin(gather.comments, gatherComment)
                .fetchJoin()
                .where(
                        gather.id.eq(id),
                        isCommentDeletedFalse
                )
                .fetchOne();
    }

    private BooleanExpression keywordContains(String keyword) {
        return keyword == null
                ? null
                : gather.content.contains(keyword).or(gather.title.contains(keyword));
    }

    private BooleanExpression categoryEqual(GatherCategory category) {
        return category == null
                ? null
                : gather.category.eq(category);
    }

    private BooleanExpression skillEqual(List<Long> gatherIdsWithSkills, String getSkills) {
        return getSkills == null || gatherIdsWithSkills.isEmpty()
                ? null
                : gather.id.in(gatherIdsWithSkills);
    }

    private BooleanBuilder skillEqualString(String skills) {
        BooleanBuilder builder = new BooleanBuilder();
        QGatherSkill qGatherSkill = gatherSkill;
        if(skills==null || skills.isEmpty() ) return  builder;
        String[] split = skills.split(",");
        for (String c : split) {
            builder.or(qGatherSkill.skill.in(Skill.valueOf(c)));
        }
        return builder;
    }




    public GatherSearchRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
