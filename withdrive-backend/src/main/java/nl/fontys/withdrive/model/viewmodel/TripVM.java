package nl.fontys.withdrive.model.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.model.Trip;
import nl.fontys.withdrive.model.dto.TripApplicationDTO;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trips")
public class TripVM extends Trip {
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter
    UUID driver;

    @Transient
    private @Getter @Setter
    List<UUID> passengers;

    @OneToMany(mappedBy = "trip")
    Set<TripApplicationDTO> application;
}
