package com.jwt.jwttutorial.service;


import com.jwt.jwttutorial.dto.OAuth2UserInfo;
import com.jwt.jwttutorial.dto.OAuth2UserInfoFactory;
import com.jwt.jwttutorial.entity.Users;
import com.jwt.jwttutorial.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.AuthProvider;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        // 최종적으로 여기서 princibal 객체가 생성되면 돼
        return processOAuth2User(userRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        //OAuth2 로그인 플랫폼 구분
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Users user = usersRepository.findOneWithAuthoritiesByUserEmail(oAuth2UserInfo.getEmail()).orElse(null);
        // users에 다른 컬럼들 추가해야 할 듯
        // provider
        //이미 가입된 경우
        if (user != null) {
            if (!user.getProvider().equals(authProvider)) {
                throw new RuntimeException("Email already signed up.");
            }
            // 유저가 있고, 프로바이더도 잘 맞으면 실행 => 즉 정상적으로 로그인 했을 때 실행하는 부분
            // 뭔지는 아직 안 봄
            // 아마 추출한 DB유저 객체에 구글로그인 정보 합친다는 거겠지
            // 이거 끝내고 return으로 가
            user = updateUser(user, oAuth2UserInfo);
        }
        //가입되지 않은 경우
        else {
            user = registerUser(authProvider, oAuth2UserInfo);
        }
        // 이 크리에이트는 다른걸로 바뀌어야 할 듯. 아마 생성자?
        return UserPrincipal.create(user, oAuth2UserInfo.getAttributes());
    }

    private Users registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        // 여기가 회원가입 부분 - UserService의 회원가입 부분 참고 - 아니면 둘도 하나로 만들어서 통일?
        Users user = Users.builder()
                .userEmail(oAuth2UserInfo.getEmail())
                .userName(oAuth2UserInfo.getName())
                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
                .authProvider(authProvider)
                .role(Role.ROLE_USER)
                .build();

        return userRepository.save(user);
    }

    private Users updateUser(Users user, OAuth2UserInfo oAuth2UserInfo) {
        return usersRepository.save(user.update(oAuth2UserInfo));
    }
}
