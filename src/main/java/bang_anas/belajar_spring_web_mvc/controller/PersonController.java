package bang_anas.belajar_spring_web_mvc.controller;

import bang_anas.belajar_spring_web_mvc.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class PersonController {

    @PostMapping(path = "/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    // menggunakan @Valid untuk melakukan validation
    public ResponseEntity createPerson(@ModelAttribute @Valid CreatePersonRequest request, BindingResult bindingResult) {

        //        List<ObjectError> error = bindingResult.getAllErrors();
        List<FieldError> error = bindingResult.getFieldErrors();

        if (!error.isEmpty()) {
            error.forEach(fieldError -> {
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });
            // jika ingin menggunakan object error
            //            error.forEach(objectError -> {
            //                System.out.println(objectError.getDefaultMessage());
            //            });
            return ResponseEntity.badRequest().body("You send invalid data");
        }
        System.out.println(request);

        String response = new StringBuilder().append("Success Create Person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName()).append(" ")
                .append("with email ").append(request.getEmail()).append(" ")
                .append("and phone ").append(request.getPhone()).append(" ")
                .append("with address ").append(request.getAddress().getStreet()).append(", ")
                .append(request.getAddress().getCity()).append(", ")
                .append(request.getAddress().getCountry()).append(", ")
                .append(request.getAddress().getPostalCode())
                .toString();
        return ResponseEntity.ok(response);
    }

}
