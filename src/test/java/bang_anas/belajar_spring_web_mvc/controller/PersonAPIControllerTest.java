package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.model.CreateAddressRequest;
import bang_anas.belajar_spring_web_mvc.model.CreatePersonRequest;
import bang_anas.belajar_spring_web_mvc.model.CreateSocialMediaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        // 1. buat object baru CreatePersonRequest untuk
        // menerima semua data dari atribute milik CreatePersonRequest
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Anas");
        request.setMiddleName("Wiwok");
        request.setLastName("Nyueni");
        request.setEmail("Alamak@yahoo.com");
        request.setPhone("901209310293");

        // 2. membuat object baru CreateAddressRequest untuk
        // dapat mengirim data atau setter ke dalam object addres
        CreateAddressRequest address = new CreateAddressRequest();
        address.setStreet("Himawari");
        address.setCity("Shizuoka");
        address.setCountry("Japan");
        address.setPostalCode("992072");

        request.setHobbies(List.of("Coding","Gym","Gaming"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("We chat", "wechat.com"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("samsung cloud", "samsung.com"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }
}