package nl.fontys.withdrive.dto.tripApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.enumeration.ApplicationStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {
    private @Getter @Setter TripResponseDTO trip;
    private @Getter @Setter UserDTO user;
    private @Getter @Setter ApplicationStatus status;
    private @Getter @Setter String text;
}
