package com.playdata.pdfolio.auth;

import com.playdata.pdfolio.domain.dto.jwt.UserInfoDto;
import com.playdata.pdfolio.domain.entity.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserInfo implements UserDetails {

    private UserInfoDto userInfoDto;


    public UserInfo(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public Long getMemberId(){
        return userInfoDto.id();
    }

    public Member getMember(){
        return Member.builder()
                .id(userInfoDto.id())
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
