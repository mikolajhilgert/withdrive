package nl.fontys.withdrive.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TripDTO {
    private @Getter
    final int tripID;
    private @Getter
    final UserDTO driver;
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;
    private @Getter @Setter
    List<UserDTO> passengers;

    public TripDTO(int tripID,UserDTO driver,String origin, String destination, String description) {
        this.tripID = tripID;
        this.origin = origin;
        this.destination = destination;
        this.driver = driver;
        this.description = description;
    }
}
