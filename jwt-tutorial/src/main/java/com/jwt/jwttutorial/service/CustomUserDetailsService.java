package com.jwt.jwttutorial.service;

import com.jwt.jwttutorial.entity.UserPrincipal;
import com.jwt.jwttutorial.entity.Users;
import com.jwt.jwttutorial.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * 로그인 시 DB에서 유저정보와 권한정보를 가져온다.
    * */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userEmail) {
        return usersRepository.findOneWithAuthoritiesByUserEmail(userEmail)
                .map(user -> new UserPrincipal(user))
                .orElseThrow(() -> new UsernameNotFoundException(userEmail + " -> 해당하는 유저를 찾을 수 없습니다."));
    }

    // user의 활성화 여부 체크가 갈 곳을 잃음 => 어디로 보낼까?
//        if (!user.isActivated()) {
//            throw new RuntimeException(userEmail + " -> 활성화되어 있지 않습니다.");
//        }



    // DB Users -> UserDetails
    // (지금, 디비->디테일->프린시펄 3단계로 가는데 이거 그냥 디비에서 프린시펄로 가게 하면 좋을 듯)
    // 그런 의도로 프린시펄 만든거니까...
    // 물론 여기서 private뒤에 붙는 건 여전히 디테일 - 즉 선언은 디테일
    // 생성은 프린시펄로 하도록 여기서 끝내자
    private org.springframework.security.core.userdetails.User createUser(String userEmail, Users user) {


        List<GrantedAuthority> grantedAuthorities = user.getUserAuthoritySet().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserEmail(),
                user.getUserPassword(),
                grantedAuthorities);
    }

}
