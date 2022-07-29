package co.com.nisum.service.impl;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;
import co.com.nisum.model.mapper.UserMapper;
import co.com.nisum.repository.UserRepository;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.usersToUsersDTO((List<User>) userRepository.findAll());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user.getPhones().forEach(phone -> phone.setUser(user));
        User userCreated =  userRepository.save(user);
        return userMapper.userToUserDTO(userCreated);
    }
}
