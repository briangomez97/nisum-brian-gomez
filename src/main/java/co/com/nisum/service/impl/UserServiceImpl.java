package co.com.nisum.service.impl;

import co.com.nisum.config.security.JwtTokenUtil;
import co.com.nisum.exception.DuplicateEmailException;
import co.com.nisum.exception.UserNotFoundException;
import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;
import co.com.nisum.model.mapper.UserMapper;
import co.com.nisum.repository.UserRepository;
import co.com.nisum.service.RegularExpressionService;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final RegularExpressionService regularExpressionService;

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUsersDTO(users);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateExistenceEmail(userDTO.getEmail());
        User user = userMapper.userDTOToUser(userDTO);
        user.getPhones().forEach(phone -> phone.setUser(user));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        jwtTokenUtil.assignSecurityPropertiesUser(user);
        return saveUser(user);
    }

    private void validateExistenceEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()) {
            throw new DuplicateEmailException(String.format("The email '%s' already exists in the database", email));
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email '%s' not found", email)));
    }

    @Override
    public UserDTO saveUser(User user) {
        return userMapper.userToUserDTO(userRepository.save(user));
    }


}
