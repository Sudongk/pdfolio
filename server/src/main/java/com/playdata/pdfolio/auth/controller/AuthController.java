package com.playdata.pdfolio.auth.controller;

import com.playdata.pdfolio.auth.service.AuthService;
import com.playdata.pdfolio.domain.dto.jwt.TokenDto;
import com.playdata.pdfolio.domain.request.auth.AuthRequest;
import com.playdata.pdfolio.domain.request.auth.TokenRenewRequest;
import com.playdata.pdfolio.domain.response.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse verify(@RequestBody AuthRequest authRequest){
        return authService.verify(authRequest);
    }

    @PostMapping("/renew")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto renew(@RequestBody TokenRenewRequest tokenRenewRequest)
    {
        return authService.renew(tokenRenewRequest);
    }
}
