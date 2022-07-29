package co.com.nisum.controller;

import co.com.nisum.controller.response.SuccessResponse;
import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public SuccessResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new SuccessResponse<>(userService.createUser(userDTO), HttpStatus.CREATED.value());
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
