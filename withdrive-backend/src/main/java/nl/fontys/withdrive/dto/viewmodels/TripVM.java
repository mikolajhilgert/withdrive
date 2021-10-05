package nl.fontys.withdrive.dto.viewmodels;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.dto.Trip;
import nl.fontys.withdrive.dto.TripDTO;

import java.util.List;
import java.util.UUID;

public class TripVM extends Trip {

    private @Getter @Setter
    UUID driver;
    private @Getter @Setter
    List<UUID> passengers;

//    public TripVM(UUID tripID, String origin, String destination, String description, UUID driver, List<UUID> passengers) {
//        super(tripID, origin, destination, description);
//        this.driver = driver;
//        this.passengers = passengers;
//    }

    public TripVM(String origin, String destination, String description, UUID driver, List<UUID> passengers) {
        super(UUID.randomUUID(), origin, destination, description);
        this.driver = driver;
        this.passengers = passengers;
    }

}
