package co.com.nisum.service;

import co.com.nisum.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

}
