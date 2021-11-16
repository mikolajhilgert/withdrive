package nl.fontys.withdrive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.entity.Role;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private @Getter @Setter UUID userID = UUID.randomUUID();
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String dateOfBirth;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String gender;

    private @Getter @Setter String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter Collection<Role> roles;
}
