package com.playdata.pdfolio.domain.entity.heart;

import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class GatherHeart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 좋아요 번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; // 좋아요 한 유저

    @ManyToOne(fetch = FetchType.LAZY)
    private Gather gather; // 좋아요 한 게시물
}
