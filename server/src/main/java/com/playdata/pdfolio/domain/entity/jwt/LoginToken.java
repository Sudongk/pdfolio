package com.playdata.pdfolio.domain.entity.jwt;

import com.playdata.pdfolio.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class LoginToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshToken;
    @OneToOne
    private Member member;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
