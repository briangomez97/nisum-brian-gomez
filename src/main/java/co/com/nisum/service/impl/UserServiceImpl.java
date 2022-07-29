package co.com.nisum.service.impl;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;
import co.com.nisum.model.factory.UserFactory;
import co.com.nisum.repository.UserRepository;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(UserFactory.createUser(userDTO));
    }
}
