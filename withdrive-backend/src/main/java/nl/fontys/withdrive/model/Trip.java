package nl.fontys.withdrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Trip {
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    protected @Getter @Setter UUID tripID = UUID.randomUUID();
    protected @Getter @Setter String origin;
    protected @Getter @Setter String destination;
    protected @Getter @Setter String description;

}



