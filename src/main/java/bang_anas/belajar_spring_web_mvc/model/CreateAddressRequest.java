package bang_anas.belajar_spring_web_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAddressRequest {

    private String street;

    private String city;

    private String country;

    private String postalCode;

}
