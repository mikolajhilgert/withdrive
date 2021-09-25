package nl.fontys.withdrive.dto.viewmodels;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.dto.Trip;
import nl.fontys.withdrive.dto.TripDTO;

import java.util.List;

public class TripVM extends Trip {

    private @Getter @Setter
    int driver;
    private @Getter @Setter
    List<Integer> passengers;

    public TripVM(int tripID, String origin, String destination, String description, int driver, List<Integer> passengers) {
        super(tripID, origin, destination, description);
        this.driver = driver;
        this.passengers = passengers;
    }

    public TripVM(TripDTO trip){
        super(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription());
    }
}
