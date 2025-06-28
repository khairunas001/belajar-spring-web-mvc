package bang_anas.belajar_spring_web_mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FormControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createpersonTest() throws Exception {
        // 1. URL
        String url = "http://localhost:" + port + "/form/person";

        // 2. Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 3. Set body (form-urlencoded)
        String body = "name=anas&birthDate=2001-01-01&addres=Jakarta";
        HttpEntity<String> requestEntity = new HttpEntity<>(
                body,
                headers
        );

        // 4. Send POST request
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                url,
                requestEntity,
                String.class
        );

        // 5. Assertion
        String response = responseEntity.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(
                "Succes create Person with Name : anas, Birth Date : 2001-01-01, addres : Jakarta",
                response.trim()
        );

        // 6. Log (opsional)
        log.info(
                "Response body = {}",
                response
        );
        log.info(
                "Response entity = {}",
                responseEntity
        );
    }
}
