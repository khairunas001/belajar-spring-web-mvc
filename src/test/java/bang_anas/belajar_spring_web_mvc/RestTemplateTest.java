package bang_anas.belajar_spring_web_mvc;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private RestTemplate restTemplate;


    @Test
    void addTodo() {
        // Menyusun URL endpoint yang akan diuji
        String url = "http://localhost:" + port + "/todos";

        // Menyiapkan header HTTP untuk menerima JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // Menyiapkan form data yang akan dikirim ke server
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("todo", "Sinau Spring WebMVC"); // Harus sesuai dengan nama parameter @RequestParam di controller

        // Membuat request POST dengan body form dan header
        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(
                form,
                headers,
                HttpMethod.POST,
                URI.create(url)
        );

        // Mengirim request dan menerima response berupa List<String>
        ResponseEntity<List<String>> response = restTemplate.exchange(
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // Memastikan status response adalah 200 OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Memastikan isi response mengandung teks yang dikirim
        Assertions.assertTrue(response.getBody().toString().contains("Sinau Spring WebMVC"));

        // Memastikan item pertama pada list adalah teks yang dikirim
        Assertions.assertEquals("Sinau Spring WebMVC", response.getBody().get(0));

    }


    @Test
    void getTodo() {

        // Menyusun URL endpoint GET yang akan diuji
        String url = "http://localhost:" + port + "/todos";

        // Menyiapkan header untuk menerima JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // Membuat request GET tanpa body
        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(
                headers,
                HttpMethod.GET,
                URI.create(url)
        );

        // Mengirim request GET dan menerima response berupa List<String>
        ResponseEntity<List<String>> response = restTemplate.exchange(
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // Memastikan response status adalah 200 OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Memastikan hasil list mengandung todo yang sebelumnya ditambahkan
        Assertions.assertTrue(response.getBody().toString().contains("Sinau Spring WebMVC"));

        // Memastikan item pertama pada list adalah todo yang ditambahkan
        Assertions.assertEquals("Sinau Spring WebMVC", response.getBody().get(0));
    }
}
