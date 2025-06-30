package bang_anas.belajar_spring_web_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//jika mengggunakan lombok
//@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class HelloResponse {

    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
