package bang_anas.belajar_spring_web_mvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @GetMapping(path = "/date")
    @ResponseBody
    public String getDate(@RequestParam(name = "date") Date date) throws IOException {
        return "Date : " + dateFormat.format(date);
    }

    //    @GetMapping(path = "/date")
    //    public void getDate(@RequestParam(name = "date") Date date, HttpServletResponse response) throws IOException {
    //        String responseBody = "Date : " + dateFormat.format(date);
    //        response.getWriter().println(responseBody);
    //    }
}
