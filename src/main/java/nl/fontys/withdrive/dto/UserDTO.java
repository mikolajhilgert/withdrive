package nl.fontys.withdrive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class UserDTO {
    private @Getter
    final int clientNumber;
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String dateOfBirth;
    private @Getter @Setter String gender;
    private @Getter @Setter String phoneNumber;
    private @Getter @Setter String password;

    public UserDTO(int clientNumber, String email, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String password){
        this.clientNumber = clientNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}




