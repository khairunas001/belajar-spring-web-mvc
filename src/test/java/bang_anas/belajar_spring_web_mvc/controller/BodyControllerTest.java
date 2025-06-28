package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.model.HelloRequest;
import bang_anas.belajar_spring_web_mvc.model.HelloResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class BodyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bodyHello() throws Exception {

        HelloRequest request = new HelloRequest();
        request.setName("Anas");
        log.info(String.valueOf(request));

        String requestJson = objectMapper.writeValueAsString(request);
        log.info(requestJson);

        mockMvc.perform(post("/nody/hello")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJson)
        ).andExpectAll(
                status().isOk(),
                header().string(
                        HttpHeaders.CONTENT_TYPE,
                        Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)
                )
        ).andExpect(result -> {
            String responseJson = result.getResponse().getContentAsString();

            log.info(responseJson);

            HelloResponse response = objectMapper.readValue(
                    responseJson,
                    HelloResponse.class
            );
            log.info(String.valueOf(response));
            assertEquals(
                    "Hello Anas",
                    response.getHello()
            );
        });

    }


}