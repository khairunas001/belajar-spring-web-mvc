package bang_anas.belajar_spring_web_mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloGuest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/hello",
                String.class
        );
        String response = responseEntity.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Guest", response.trim());

        log.info("Response  void helloGuest() = {}", response);
        log.info("Response Entity  void helloGuest() = {}", responseEntity);
    }

    @Test
    void helloName() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/hello?name=Joko", String.class);
        String response = responseEntity.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Joko", response.trim());

        log.info("Response void helloName() = {}", response);
        log.info("Response Entity void helloName() = {}", responseEntity);
    }


}
