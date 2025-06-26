package bang_anas.belajar_spring_web_mvc.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImplementation implements HelloService {

    @Override
    public String hello(String name) {
        if (name == null) {
            return "Hello Guest";
        } else {
            return "Hello " + name;
        }
    }
}
