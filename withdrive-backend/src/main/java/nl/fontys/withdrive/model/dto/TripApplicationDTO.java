package nl.fontys.withdrive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.model.TripApplicationKEY;
import nl.fontys.withdrive.model.viewmodel.TripVM;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="applications")
public class TripApplicationDTO {
    @EmbeddedId
    TripApplicationKEY id;
//    private @Getter @Setter
//    @Type(type="org.hibernate.type.UUIDCharType")
//    UUID ApplicationID = UUID.randomUUID();

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID")
    private @Getter @Setter UserDTO applicant;

    @ManyToOne
    @MapsId("tripID")
    @JoinColumn(name = "tripID")
    private @Getter @Setter TripVM trip;

    @Enumerated(EnumType.STRING)
    private @Getter @Setter ApplicationStatus status = ApplicationStatus.PENDING;
    private @Getter @Setter String text;
}
