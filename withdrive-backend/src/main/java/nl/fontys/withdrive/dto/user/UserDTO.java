package nl.fontys.withdrive.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.entity.Role;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private @Getter @Setter UUID userID = UUID.randomUUID();
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String dateOfBirth;
    private @Getter @Setter String gender;
    private @Getter @Setter String phoneNumber;
    private @Getter @Setter String password;
    private @Getter @Setter Collection<Role> roles;
}
