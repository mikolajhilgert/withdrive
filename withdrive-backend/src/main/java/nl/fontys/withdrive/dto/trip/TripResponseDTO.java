package nl.fontys.withdrive.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.dto.user.UserDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDTO {
    private @Getter @Setter UUID tripID;
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;
    private @Getter @Setter UserDTO driver;
    private @Getter @Setter List<UserDTO> passengers = Collections.emptyList();
}
