package bang_anas.belajar_spring_web_mvc.controller;

import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(
                                "username",
                                "Anas"
                        )
                        .param(
                                "password",
                                "Mabar1969"
                        )
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success Login")),
                cookie().value(
                        "username",
                        Matchers.is("Anas")
                )
        );
    }


    @Test
    void loginFailed() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param(
                                "username",
                                "Anas"
                        )
                        .param(
                                "password",
                                "Mabar-54L4H"
                        )
        ).andExpectAll(
                status().isUnauthorized(),
                content().string(Matchers.containsString("Failed Login Unauthorized"))
        );
    }

    @Test
    void getUsersCookie() throws Exception {
        mockMvc.perform(get("/auth/user").cookie(new Cookie(
                "username",
                "Anas"
        ))).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Anas"))
        );
    }
}