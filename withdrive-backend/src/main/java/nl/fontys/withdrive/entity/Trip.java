package nl.fontys.withdrive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="trips")
public class Trip{
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter UUID tripID = UUID.randomUUID();
    private @Getter @Setter String origin;
    private @Getter @Setter String destination;
    private @Getter @Setter String description;

    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter
    @Setter
    UUID driver;

    @OneToMany(mappedBy = "trip")
    Set<TripApplication> application;
}