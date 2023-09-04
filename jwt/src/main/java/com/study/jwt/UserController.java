package com.study.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * login : 요청이 들어오면 username과 password를 서비스단의 login 메서드로 넘겨준다.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm) {
        JwtToken token = service.login(loginForm.get("username"), loginForm.get("password"));
        return ResponseEntity.ok(token);
    }

}

