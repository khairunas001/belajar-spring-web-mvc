package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(path = "/hello")
    public void helloWorld(@RequestParam(name = "name", required = false) String name, HttpServletResponse response) throws IOException {
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);
    }

    //        @RequestMapping(path = "/hello", method = RequestMethod.GET)

    //    @GetMapping(path = "/hello")
    //    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //        String name = request.getParameter("name");
    //        String responseBody = helloService.hello(name);
    //        response.getWriter().println(responseBody);
    //    }


    @GetMapping(path = "/web/hello")
    public ModelAndView hello(@RequestParam(name = "name", required = false) String name) {
        if(Objects.isNull(name)){
            return new ModelAndView("redirect:/web/hello?name=Tamu Tercintaku");
        }
        return new ModelAndView(
                "hello", // "hello" disini adalah nama file dari file mustache-nya
                Map.of(
                        "title", "Sinau Mustache Spring",
                        "name", name
                )
        );
    }
}
