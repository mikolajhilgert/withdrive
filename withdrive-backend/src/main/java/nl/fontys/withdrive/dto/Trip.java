package nl.fontys.withdrive.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

//@Entity
//@NoArgsConstructor
public abstract class Trip {
//    @Id
//    @GeneratedValue
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

//    public Trip(String origin, String destination, String description) {
//        this.tripID = UUID.randomUUID();
//        this.origin = origin;
//        this.destination = destination;
//        this.description = description;
//    }

}

