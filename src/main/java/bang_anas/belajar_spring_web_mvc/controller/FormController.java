package bang_anas.belajar_spring_web_mvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormController {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // jika tanpa object SimpleDateFormat maka akan mendapatkan hasil seperti ini " Mon Jan 01 00:00:00 WIB 2001, "
    @PostMapping(path = "/form/person")
    @ResponseBody
    public String createPerson(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "birthDate") Date birthDate,
            @RequestParam(name = "addres") String addres) {
        return "Succes create Person with Name : " + name + ", Birth Date : " + dateFormat.format(birthDate) + ", " +
                "addres : " + addres;
    }

    @PostMapping(path = "/form/hello", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name) {
        return """
                <html>
                <body>
                <h1>Hello $name</h1>
                </body>
                </html>
                """.replace(
                "$name",
                name
        );
    }

    //    @PostMapping(path = "/form/hello", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //    @ResponseBody
    //    public String hello(@RequestParam(name = "name") String name) {
    //        return "Hello " + name;
    //    }

}
