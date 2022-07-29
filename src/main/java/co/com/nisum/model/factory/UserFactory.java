package co.com.nisum.model.factory;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;

public class UserFactory {

    public static User createUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
