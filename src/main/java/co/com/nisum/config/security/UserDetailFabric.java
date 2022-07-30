package co.com.nisum.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserDetailFabric {

    private final static String USER_ROLE = "USER_ROLE";

    public static UserDetails createUserDetail(co.com.nisum.model.entity.User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(USER_ROLE));
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorityList)
                .disabled(!user.getIsActive())
                .build();
    }
}
