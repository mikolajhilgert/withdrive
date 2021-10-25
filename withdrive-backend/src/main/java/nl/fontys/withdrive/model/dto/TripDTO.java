package nl.fontys.withdrive.model.dto;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.model.Trip;

import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

//@Entity
@Table(name="trip")
public class TripDTO extends Trip {
    private @Getter @Setter UserDTO driver;
    private @Getter @Setter List<UserDTO> passengers;

    public TripDTO(UUID tripID, String origin, String destination, String description, UserDTO driver, List<UserDTO> passengers) {
        super(tripID, origin, destination, description);
        this.driver = driver;
        this.passengers = passengers;
    }
}
