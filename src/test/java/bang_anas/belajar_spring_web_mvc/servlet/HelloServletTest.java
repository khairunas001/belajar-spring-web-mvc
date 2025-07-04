package bang_anas.belajar_spring_web_mvc.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServletTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloServlet() {
        String response = restTemplate.getForObject(
                "http://localhost:" + port + "/servlet/hello",
                String.class
        );

        Assertions.assertEquals(
                "Hello ini dari posisi Servlet",
                response.trim()
        );

    }
}