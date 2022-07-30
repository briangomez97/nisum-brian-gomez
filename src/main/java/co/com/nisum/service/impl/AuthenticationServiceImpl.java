package co.com.nisum.service.impl;

import co.com.nisum.config.jwt.JwtTokenUtil;
import co.com.nisum.config.jwt.UserDetailFabric;
import co.com.nisum.exception.UnauthorizedUserException;
import co.com.nisum.model.entity.User;
import co.com.nisum.service.AuthenticationService;
import co.com.nisum.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    public String authenticate(String email, String password) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (auth.isAuthenticated()) {
            User user = userService.getUserByEmail(email);
            try {
                if (jwtTokenUtil.validateToken(user.getToken(), UserDetailFabric.createUserDetail(user))) {
                    user.setLastLogin(LocalDateTime.now());
                }
            } catch(ExpiredJwtException ex){
                log.warn(String.format("User's token with email '%s' has expired. Generating and saving new token", email));
                jwtTokenUtil.assignSecurityPropertiesUser(user);
            }
            userService.saveUser(user);
            StringBuilder messageResponse = new StringBuilder("Bearer ");
            return messageResponse.append(user.getToken()).toString();
        }
        throw new UnauthorizedUserException("Authentication failed, check the supplied fields");
    }
}
