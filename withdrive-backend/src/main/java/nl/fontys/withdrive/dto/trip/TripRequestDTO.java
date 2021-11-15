package nl.fontys.withdrive.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.enumeration.TripStatus;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDTO {
    private @Getter @Setter UUID tripID = UUID.randomUUID();
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;
    private @Getter @Setter String date;
    private @Getter @Setter String licensePlate;
    private @Getter @Setter int maxPassengers;
    private @Getter @Setter double pricePerPassenger;
    private @Getter @Setter UUID driver;
    private @Getter @Setter List<UUID> passengers;
    private @Getter @Setter TripStatus status = TripStatus.AWAITING;
}
