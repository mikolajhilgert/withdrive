package nl.fontys.withdrive.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TripDTO extends Trip{
    private @Getter @Setter UserDTO driver;
    private @Getter @Setter List<UserDTO> passengers;

    public TripDTO(int tripID, String origin, String destination, String description, UserDTO driver, List<UserDTO> passengers) {
        super(tripID, origin, destination, description);
        this.driver = driver;
        this.passengers = passengers;
    }
}
