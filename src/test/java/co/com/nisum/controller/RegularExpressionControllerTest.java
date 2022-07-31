package co.com.nisum.controller;

import co.com.nisum.ApplicationMock;
import co.com.nisum.builder.RegularExpressionDTODataBuilder;
import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.util.enums.RegexEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ApplicationMock.class)
public class RegularExpressionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllRegex() throws Exception {
        mockMvc.perform(get("/regex"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.body.size()", is(2)))
                .andExpect(jsonPath("$.response.body[0].name", is(RegexEnum.EMAIL_REGEX.getValue())))
                .andExpect(jsonPath("$.response.body[1].name", is(RegexEnum.PASSWORD_REGEX.getValue())));
    }

    @Test
    public void getRegexByName() throws Exception {
        mockMvc.perform(get("/regex/{regexEnum}", RegexEnum.EMAIL_REGEX))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.body.name", is(RegexEnum.EMAIL_REGEX.getValue())));
    }

    @Test
    public void getRegexByNameNotFound() throws Exception {
        mockMvc.perform(get("/regex/{regexEnum}", "NameNotExists"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.exceptionName", is("RegularExpressionNotExistException")))
                .andExpect(jsonPath("$.error.message", is("There is no regular expression with 'NameNotExists' name")));
    }

    @Test
    public void updateRegexByName() throws Exception {
        RegularExpressionDTO regularExpressionDTO = new RegularExpressionDTODataBuilder().withIdField().build();

        mockMvc.perform(put("/regex/{regexEnum}", RegexEnum.EMAIL_REGEX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regularExpressionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.body.regularexpression", is(regularExpressionDTO.getRegularExpression())));
    }

    @Test
    public void updateRegexByNameNotFound() throws Exception {
        RegularExpressionDTO regularExpressionDTO = new RegularExpressionDTODataBuilder().withIdField().build();

        mockMvc.perform(put("/regex/{regexEnum}", "NameNotExists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regularExpressionDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.exceptionName", is("RegularExpressionNotExistException")))
                .andExpect(jsonPath("$.error.message", is("There is no regular expression with 'NameNotExists' name")));
    }
}
