package co.com.nisum.controller;

import co.com.nisum.model.entity.User;
import co.com.nisum.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private String a;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public StringBuilder getAllUsers() {
        return new StringBuilder("Hola");
    }
}
