package nl.fontys.withdrive.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public abstract class Trip {
    protected @Getter @Setter
    UUID tripID;
    protected @Getter @Setter String origin;
    protected @Getter @Setter String destination;
    protected @Getter @Setter String description;

    public Trip(UUID tripID, String origin, String destination, String description) {
        this.tripID = tripID;
        this.origin = origin;
        this.destination = destination;
        this.description = description;
    }

}

