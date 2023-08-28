package com.playdata.pdfolio.oauth2.service;

import com.playdata.pdfolio.auth.service.AuthService;
import com.playdata.pdfolio.domain.dto.jwt.TokenDto;
import com.playdata.pdfolio.domain.dto.oauth2.LoginInfoDto;
import com.playdata.pdfolio.domain.entity.common.Skill;
import com.playdata.pdfolio.domain.entity.member.Member;
import com.playdata.pdfolio.domain.entity.member.MemberSkill;
import com.playdata.pdfolio.domain.entity.oauth2.Oauth2AccessToken;
import com.playdata.pdfolio.domain.entity.oauth2.Oauth2UserInfo;
import com.playdata.pdfolio.domain.request.oauth2.LoginRequest;
import com.playdata.pdfolio.domain.request.oauth2.SignupRequest;
import com.playdata.pdfolio.domain.response.oauth2.Oauth2Response;
import com.playdata.pdfolio.domain.response.oauth2.Oauth2StatusResponse;
import com.playdata.pdfolio.member.exception.UnregisteredMemberException;
import com.playdata.pdfolio.member.repository.MemberRepository;
import com.playdata.pdfolio.member.repository.MemberSkillRepository;
import com.playdata.pdfolio.oauth2.provider.Oauth2Provider;
import com.playdata.pdfolio.oauth2.provider.ProviderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class Oauth2Service {

    private final ProviderFactory providerFactory;
    private final AuthService authService;
    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;


    public Oauth2Response login(String providerName, LoginRequest loginRequest){
        Oauth2UserInfo userInfo = getUserInfo(providerName, loginRequest.accessToken());
        Member member = memberRepository
                .findByProviderIdAndProviderName(
                        userInfo.getProviderId(),
                        userInfo.getProviderName())
                .orElseThrow(UnregisteredMemberException::new);

        TokenDto token = authService.createToken(member);
        LoginInfoDto loginInfoDto = new LoginInfoDto(
                member.getId(),
                providerName,
                userInfo.getUserName(),
                member.getNickName());
        return new Oauth2Response(loginInfoDto, token);
    }

    public Oauth2Response signup(String providerName, SignupRequest signupRequest) {
        Oauth2UserInfo userInfo = getUserInfo(providerName, signupRequest.accessToken());

        Member member = memberRepository.save(
                Member.builder()
                        .name(userInfo.getUserName())
                        .nickName(signupRequest.nickName())
                        .providerId(userInfo.getProviderId())
                        .providerName(userInfo.getProviderName())
                        .imageUrl(signupRequest.imageUrl())
                        .build());

        Set<MemberSkill> memberSkills = Skill.of(signupRequest.skills()).stream()
                .map(skill -> MemberSkill.builder()
                        .skill(skill)
                        .member(member)
                        .build())
                .collect(Collectors.toSet());

        memberSkillRepository.saveAll(memberSkills);

        TokenDto token = authService.createToken(member);
        LoginInfoDto loginInfoDto = new LoginInfoDto(
                member.getId(),
                providerName,
                userInfo.getUserName(),
                member.getNickName());
        return new Oauth2Response(loginInfoDto, token);
    }

    public Oauth2StatusResponse check(String providerName, String code) {
        Oauth2AccessToken accessToken = getAccessToken(providerName, code);
        Oauth2UserInfo userInfo = getUserInfo(providerName, accessToken.token());

        Optional<Member> member = memberRepository.findByProviderIdAndProviderName(
                userInfo.getProviderId(),
                userInfo.getProviderName()
        );

        return new Oauth2StatusResponse(
                member.isEmpty(),
                providerName,
                accessToken.token());
    }

    private Oauth2AccessToken getAccessToken(String providerName, String code) {
        Oauth2Provider provider = providerFactory.getProvider(providerName);
        return WebClient.create()
                .post()
                .uri(provider.getAccessTokenUri())
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(Oauth2AccessToken.class)
                .block();
    }

    private Oauth2UserInfo getUserInfo(String providerName, String accessToken) {
        Oauth2Provider provider = providerFactory.getProvider(providerName);
        Map<String, Object> attributes = WebClient.create()
                .get()
                .uri(provider.getUserInfoUri())
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        return Oauth2UserInfo.of(provider.getName(), attributes);
    }

    private MultiValueMap<String, String> tokenRequest(String code, Oauth2Provider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", provider.getClientId());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        return formData;
    }
}
