package nl.fontys.withdrive.dto.tripApplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.enumeration.ApplicationStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDTO {
    private @Getter @Setter UUID trip;
    private @Getter @Setter UUID user;
    private @Getter @Setter ApplicationStatus status = ApplicationStatus.PENDING;
    private @Getter @Setter String text;
}
