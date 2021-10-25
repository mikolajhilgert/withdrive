package nl.fontys.withdrive.model.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.model.Trip;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name="trip")
public class TripVM extends Trip {
    private @Getter @Setter
    UUID driver;

    @Transient
    private @Getter @Setter
    List<UUID> passengers;

    public TripVM(UUID tripID, String origin, String destination, String description, UUID driver, List<UUID> passengers) {
        super(tripID, origin, destination, description);
        this.driver = driver;
        this.passengers = passengers;
    }
}
