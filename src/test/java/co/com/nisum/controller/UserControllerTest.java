package co.com.nisum.controller;

import co.com.nisum.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ApplicationMock.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.response.body.size()", is(3)))
                .andExpect(jsonPath("$.response.body[0].email", is("paolo1@mail.com")))
                .andExpect(jsonPath("$.response.body[1].email", is("paolo2@mail.com")))
                .andExpect(jsonPath("$.response.body[2].email", is("paolo3@mail.com")));
    }
}
