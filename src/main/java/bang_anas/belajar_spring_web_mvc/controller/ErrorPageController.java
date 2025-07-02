package bang_anas.belajar_spring_web_mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping(path = "/error")
    public ResponseEntity<String> error(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();

        String html = """
                <html>
                <body>
                <h1> $status - $message </h1>
                </body>
                </html>
                """.replace(
                        "$status",
                        status.toString()
                )
                .replace(
                        "$message",
                        message.toString()
                );

        return new ResponseEntity<>(
                html,
                HttpStatusCode.valueOf(status)
        );
        //        return ResponseEntity.status(status).body(html);

    }


}
