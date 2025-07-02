package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> responseEntity(@RequestParam("username") String username,
                                                 @RequestParam("password") String password,
                                                 HttpServletRequest servletRequest,
                                                 HttpServletResponse servletResponse
    ) {
        if (username.equals("Anas") && (password.equals("Mabar1969"))) {

            // buat session
            HttpSession session = servletRequest.getSession(true);
            session.setAttribute(
                    "user",
                    new User(username)
            );

            // buat cookie
            Cookie cookie = new Cookie(
                    "username",
                    username
            );
            cookie.setPath("/");
            servletResponse.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body("Success Login");

            // Cara Biasa
            //            return new ResponseEntity<>(
            //                    "Success Login",
            //                    HttpStatus.OK
            //            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed Login Unauthorized");

            // Cara Biasa
            //            return new ResponseEntity<>(
            //                    "Failed Login Unauthorzed",
            //                    HttpStatus.UNAUTHORIZED
            //            );

        }
    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getUser(@CookieValue("username") String username) {
        return new ResponseEntity<>(
                "Hello " + username,
                HttpStatus.OK
        );
    }
}
