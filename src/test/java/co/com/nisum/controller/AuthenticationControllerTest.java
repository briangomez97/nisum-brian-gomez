package co.com.nisum.controller;

import co.com.nisum.ApplicationMock;
import co.com.nisum.builder.RegularExpressionDTODataBuilder;
import co.com.nisum.builder.UserDTODataBuilder;
import co.com.nisum.config.jwt.JwtTokenUtil;
import co.com.nisum.config.jwt.UserDetailFabric;
import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.mapper.UserMapper;
import co.com.nisum.service.AuthenticationService;
import co.com.nisum.util.enums.RegexEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ApplicationMock.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void createUser() throws Exception {
        UserDTO userToSaveDTO = new UserDTODataBuilder().build();
        UserDTO userSaved = new UserDTODataBuilder().withIsActiveField().build();

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToSaveDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.body.isactive", is(userSaved.getIsActive())));
    }

    @Test
    public void createUserEmailValidationError() throws Exception {
        UserDTO userToSaveDTO = new UserDTODataBuilder().withEmailField("badformat@mail").build();

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToSaveDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.exceptionName", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.error.fieldErrors[0].field", is("email")))
                .andExpect(jsonPath("$.error.fieldErrors[0].message", is("invalid 'email' format")));
    }

    @Test
    public void createUserPasswordValidationError() throws Exception {
        UserDTO userToSaveDTO = new UserDTODataBuilder().withPasswordField("badformat").build();

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToSaveDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.exceptionName", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.error.fieldErrors[0].field", is("password")))
                .andExpect(jsonPath("$.error.fieldErrors[0].message", is("invalid 'password' format")));
    }

    @Test
    public void createUserEmailDuplicatedError() throws Exception {
        UserDTO userToSaveDTO = new UserDTODataBuilder().withEmailField("paolo1@mail.com").build();

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToSaveDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.exceptionName", is("DuplicateEmailException")))
                .andExpect(jsonPath("$.error.message", is("The email 'paolo1@mail.com' already exists in the database")));
    }

    @Test
    public void loginUser() throws Exception {
        given(authenticationService.authenticate(any(String.class), any(String.class))).willReturn("tokenhere");
        mockMvc.perform(post("/auth/login")
                        .param("email", "paolo@mail.com")
                        .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.message", is("tokenhere")));
    }

    @Test
    public void loginUserNotFoundError() throws Exception {
        given(authenticationService.authenticate(any(String.class), any(String.class))).willThrow(new InternalAuthenticationServiceException("User with email 'paolo@mail.com' not found"));
        mockMvc.perform(post("/auth/login")
                        .param("email", "paolo@mail.com")
                        .param("password", "123"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error.exceptionName", is("InternalAuthenticationServiceException")))
                .andExpect(jsonPath("$.error.message", is("User with email 'paolo@mail.com' not found")));
    }
}
