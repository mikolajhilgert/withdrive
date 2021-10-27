package nl.fontys.withdrive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private @Getter @Setter UUID userID;
    private @Getter @Setter String email;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String dateOfBirth;
    private @Getter @Setter String gender;
    private @Getter @Setter String phoneNumber;
    private @Getter @Setter String password;

    @OneToMany(mappedBy = "applicant")
    Set<TripApplication> application;

//    public User(UUID userID, String email, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String password) {
//        //this.userID = Objects.requireNonNullElseGet(userID, UUID::randomUUID);
//        this.userID = userID;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.gender = gender;
//        this.phoneNumber = phoneNumber;
//        this.password = password;
//    }
}







