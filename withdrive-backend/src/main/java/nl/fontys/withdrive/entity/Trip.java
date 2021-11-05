package nl.fontys.withdrive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.enumeration.TripStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trips")
public class Trip{
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter UUID tripID;
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;

    @Enumerated(EnumType.STRING)
    private @Getter @Setter TripStatus status;

    @OneToMany(mappedBy = "trip")
    Set<TripApplication> application;

    @ManyToOne
    private @Getter @Setter User driver;
}