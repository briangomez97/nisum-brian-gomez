package co.com.nisum.service;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(UserDTO userDTO);

}
