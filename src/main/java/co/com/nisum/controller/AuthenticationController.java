package co.com.nisum.controller;

import co.com.nisum.controller.response.SuccessResponse;
import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.service.AuthenticationService;
import co.com.nisum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public SuccessResponse loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return new SuccessResponse(authenticationService.authenticate(email, password), HttpStatus.OK.value());
    }

    @PostMapping("/register")
    public SuccessResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new SuccessResponse<>(userService.createUser(userDTO), HttpStatus.CREATED.value());
    }
}

