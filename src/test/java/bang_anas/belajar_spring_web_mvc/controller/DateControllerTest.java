package bang_anas.belajar_spring_web_mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void date() throws Exception {
        mockMvc.perform(get("/date").queryParam(
                "date",
                "2025-12-22"
        )).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("20251222"))
        );
    }

}