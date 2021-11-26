package nl.fontys.withdrive.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class UserViewDTO {
    private @Getter @Setter UUID userID;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
}
