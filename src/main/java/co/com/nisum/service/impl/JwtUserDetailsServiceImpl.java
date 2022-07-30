package co.com.nisum.service.impl;

import co.com.nisum.config.security.UserDetailFabric;
import co.com.nisum.exception.UserNotFoundException;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        co.com.nisum.model.entity.User user = userService.getUserByEmail(email);
        return UserDetailFabric.createUserDetail(user);
    }
}