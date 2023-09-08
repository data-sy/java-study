package com.jwt.jwtredis.controller;

import com.jwt.jwtredis.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody MemberDto.SignUp signUpDto) {
        MemberDto.Response response = memberService.signUp(signUpDto);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getMember(@AuthenticationPrincipal CustomUserDetails user) {
        String email = user.getEmail();
        MemberDto.Response response = memberService.findMemberByEmail(email);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
