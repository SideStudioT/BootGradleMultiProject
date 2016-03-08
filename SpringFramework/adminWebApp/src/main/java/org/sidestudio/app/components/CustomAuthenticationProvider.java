package org.sidestudio.app.components;

import org.sidestudio.app.domain.SessionUserDetails;
import org.sidestudio.app.repository.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security AuthenticationProvider 커스텀 로그인 구현 Class
 *
 * @author logan
 * @since 2016-03-08
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    PasswordEncoder passwordEncoder; // BCryptPasswordEncoder

    @Autowired
    SampleRepository sampleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        logger.info("Credentials = " + (String) authentication.getCredentials());
        logger.info("Authorities = " + authentication.getAuthorities());
        logger.info("Details = " + authentication.getDetails());
        logger.info("Principal = " + (String) authentication.getPrincipal());
        logger.info("Name = " + authentication.getName());

        String id = authentication.getName();
        String pw = (String) authentication.getCredentials();

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("id", id);

        SessionUserDetails userInfoMap = sampleRepository.selectUserInfoById(parameterMap);

        // ID에 해당하는 유저가 있는지 체크
        if(userInfoMap != null) {

            // 패스워드 체크
            if(passwordEncoder.matches(pw, userInfoMap.getPassword())) {

                // 로그인 유저 권한 부여
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(id, pw, userInfoMap.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(userInfoMap);
                return usernamePasswordAuthenticationToken;
            }
            else {

                logger.info("로그인 실패", "패스워드가 일치하지 않습니다");
                throw new BadCredentialsException("Bad credentials");
            }

        }
        else {

            logger.info("로그인 실패", "유저 정보가 없습니다");
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
