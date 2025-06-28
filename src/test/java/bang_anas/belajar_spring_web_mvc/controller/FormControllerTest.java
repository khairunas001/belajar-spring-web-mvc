package bang_anas.belajar_spring_web_mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createpersonTest() throws Exception {
        mockMvc.perform(post("/form/person").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("name","Anas")
                                .param("birthDate", "2001-12-01")
                                .param("addres", "Yogyakarta")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString(
                        "Succes create Person with Name : Anas, " +
                                "Birth Date : 2001-12-01, " +
                                "addres : Yogyakarta"))
        );
    }

    @Test
    void formHello() throws Exception {
        mockMvc.perform(post("/form/hello").contentType(MediaType.APPLICATION_FORM_URLENCODED).queryParam(
                "name",
                "Jokondo"
        )).andExpectAll(
                status().isOk(),
                header().string(
                        HttpHeaders.CONTENT_TYPE,
                        Matchers.containsString(MediaType.TEXT_HTML_VALUE)
                ),
                content().string(Matchers.containsString("Hello Jokondo"))
        );
    }
}