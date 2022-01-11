package nl.fontys.withdrive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.entity.Role;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private @Getter @Setter UUID userID = UUID.randomUUID();
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private @Getter @Setter String email;
    @Pattern(regexp = "^([^0-9]*)$")
    private @Getter @Setter String firstName;
    @Pattern(regexp = "^([^0-9]*)$")
    private @Getter @Setter String lastName;
    @Pattern(regexp = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$")
    private @Getter @Setter String dateOfBirth;
    @Pattern(regexp = "^([^0-9]*)$")
    private @Getter @Setter String gender;
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    private @Getter @Setter String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 15, message = "{validation.name.size.too_long}")
    private @Getter @Setter String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private @Getter @Setter Collection<Role> roles;
}
