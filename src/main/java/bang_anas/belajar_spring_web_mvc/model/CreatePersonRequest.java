package bang_anas.belajar_spring_web_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePersonRequest {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private CreateAddressRequest address;

    private List<String> hobbies;

    private List<CreateSocialMediaRequest> socialMedias;
}
