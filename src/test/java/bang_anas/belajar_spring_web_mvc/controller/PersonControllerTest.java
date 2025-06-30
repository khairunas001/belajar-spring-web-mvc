package bang_anas.belajar_spring_web_mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {

        mockMvc.perform(post("/person")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("firstName", "Anas")
                                .param("middleName", "Arek")
                                .param("lastName", "Nyueni")
                                .param("email","Masuk-Diakal@yahoo.com")
                                .param("phone", "0696969696")
                                .param("address.street", "Jalan Gowok")
                                .param("address.city", "Shizuoka")
                                .param("address.country", "Jepang")
                                .param("address.postalCode", "596969")
                                .param("hobbies[0]","Gaming")
                                .param("hobbies[1]","Coding")
                                .param("socialMedias[0].name","Instagram")
                                .param("socialMedias[0].location","Instagram.com")
                                .param("socialMedias[1].name","Youtube")
                                .param("socialMedias[1].location","Youtube.com")
        ).andExpectAll(
                status().isOk(),
                content().string(
                        Matchers.containsString("Success Create Person Anas " +
                                                        "Arek Nyueni with email " +
                                                        "Masuk-Diakal@yahoo.com and phone 0696969696 " +
                                                        "with address Jalan Gowok, Shizuoka, Jepang, 596969"))
        );
    }


}