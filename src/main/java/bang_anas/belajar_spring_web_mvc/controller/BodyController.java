package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.model.HelloRequest;
import bang_anas.belajar_spring_web_mvc.model.HelloResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BodyController {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(
            path = "/body/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String body(@RequestBody String requestBody) throws JsonProcessingException {
        // membuat object di class HelloRequest
        // Mengubah JSON menjadi instance dari class Java
        HelloRequest request = objectMapper.readValue(
                requestBody,
                HelloRequest.class
        );

        HelloResponse response = new HelloResponse();
        response.setHello("Hello " + request.getName());

        // Mengubah object Java menjadi JSON string
        return objectMapper.writeValueAsString(response);
    }
}
