package nl.fontys.withdrive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@NoArgsConstructor
public class UserDTO {
    private @Getter
    UUID clientNumber;
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String dateOfBirth;
    private @Getter @Setter String gender;
    private @Getter @Setter String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String password;



    public UserDTO(UUID clientNumber, String email, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String password){
        this.clientNumber = clientNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public UserDTO(String email, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String password){
        this.clientNumber = UUID.randomUUID();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}







