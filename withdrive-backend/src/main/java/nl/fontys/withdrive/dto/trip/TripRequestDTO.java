package nl.fontys.withdrive.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDTO {
    private @Getter @Setter UUID tripID;
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;
    private @Getter @Setter UUID driver;
    private @Getter @Setter List<UUID> passengers;
}
