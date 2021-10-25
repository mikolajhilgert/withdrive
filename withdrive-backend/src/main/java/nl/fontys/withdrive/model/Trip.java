package nl.fontys.withdrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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

