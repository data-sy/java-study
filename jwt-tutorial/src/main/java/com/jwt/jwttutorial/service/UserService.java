package com.jwt.jwttutorial.service;

import com.jwt.jwttutorial.dto.UserDTO;
import com.jwt.jwttutorial.entity.Authority;
import com.jwt.jwttutorial.entity.UserAuthority;
import com.jwt.jwttutorial.entity.Users;
import com.jwt.jwttutorial.exception.DuplicateMemberException;
import com.jwt.jwttutorial.exception.NotFoundMemberException;
import com.jwt.jwttutorial.repository.UserAuthorityRepository;
import com.jwt.jwttutorial.repository.UsersRepository;
import com.jwt.jwttutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, UserAuthorityRepository userAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * signup : 회원가입
     */
    @Transactional
    public UserDTO signup(UserDTO userDTO) {
        if (usersRepository.findOneWithAuthoritiesByUserEmail(userDTO.getUserEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        UserAuthority userAuthority = UserAuthority.builder()
                .authority(authority)
                .build();
        Users user = Users.builder()
                .userEmail(userDTO.getUserEmail())
                .userPassword(passwordEncoder.encode(userDTO.getUserPassword()))
                .userName(userDTO.getUserName())
                .userPhone(userDTO.getUserPhone())
                .userAuthoritySet(Collections.singleton(userAuthority))
                .activated(true)
                .build();
        Users saveUser = usersRepository.save(user);

        // user_authority 테이블에 user_id 갱신
        user.setUserId(saveUser.getUserId());
        userAuthority.setUser(user);
        userAuthorityRepository.save(userAuthority);

        return UserDTO.from(saveUser);
    }

    /**
     * getUserWithAuthorities : userEmail에 따른 유저 정보, 권한 정보를 가져온다.
     */
    @Transactional(readOnly = true)
    public UserDTO getUserWithAuthorities(String userEmail) {
        return UserDTO.from(usersRepository.findOneWithAuthoritiesByUserEmail(userEmail).orElse(null));
    }

    /**
     * getUserWithAuthorities : 현재 Security Context에 저장되어 있는 userEmail에 따른 유저 정보, 권한 정보를 가져온다.
     */
    @Transactional(readOnly = true)
    public UserDTO getMyUserWithAuthorities() {
        return UserDTO.from(
                SecurityUtil.getCurrentUserEmail()
                        .flatMap(usersRepository::findOneWithAuthoritiesByUserEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

}
