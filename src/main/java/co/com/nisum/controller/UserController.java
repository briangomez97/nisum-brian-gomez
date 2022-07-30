package co.com.nisum.controller;

import co.com.nisum.controller.response.SuccessResponse;
import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public SuccessResponse<List<UserDTO>> getAllUsers() {
        return new SuccessResponse<>(userService.getAllUsers(), HttpStatus.OK.value());
    }
}
