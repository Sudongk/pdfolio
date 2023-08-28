package com.playdata.pdfolio.auth;

import com.playdata.pdfolio.auth.exception.TokenNotFoundException;
import com.playdata.pdfolio.domain.dto.jwt.UserInfoDto;
import com.playdata.pdfolio.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken)){
            Authentication authentication = getAuthentication(bearerToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String bearerToken) {
        UserInfoDto userInfo = jwtService.getUserInfo(bearerToken);
        return new UsernamePasswordAuthenticationToken(new UserInfo(userInfo), "");
    }


}
