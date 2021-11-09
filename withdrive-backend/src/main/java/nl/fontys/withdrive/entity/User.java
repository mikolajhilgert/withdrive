package nl.fontys.withdrive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToMany(fetch=FetchType.EAGER)
    private @Getter @Setter Collection<Role> roles = new ArrayList<>();


    @OneToMany(
            mappedBy = "driver",
            cascade ={ CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST}
    )
    @JsonIgnore
    private List<Trip> trips = new ArrayList<>();


    @OneToMany(mappedBy = "applicant")
    Set<TripApplication> application;

    public User(UUID userID,String email, String firstName, String lastName,String dateOfBirth,String gender, String phoneNumber,String password){
        this.userID = userID;
    }
}







