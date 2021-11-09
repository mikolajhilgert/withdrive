package nl.fontys.withdrive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter UUID id;
    private @Getter @Setter String name;
}
