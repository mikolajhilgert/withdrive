package nl.fontys.withdrive.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class TripApplicationKEY implements Serializable {
    @Column(name = "userID")
    @Type(type="org.hibernate.type.UUIDCharType")
    UUID userID;

    @Column(name = "tripID")
    @Type(type="org.hibernate.type.UUIDCharType")
    UUID tripID;
}
