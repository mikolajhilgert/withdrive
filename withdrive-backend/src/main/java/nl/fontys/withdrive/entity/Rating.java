package nl.fontys.withdrive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.fontys.withdrive.enumeration.RatingType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ratings")
public class Rating {
    @EmbeddedId
    RatingKey id = new RatingKey();

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID")
    private @Getter @Setter User user;

    @ManyToOne
    @MapsId("raterID")
    @JoinColumn(name = "raterID")
    private @Getter @Setter User rater;

    @ManyToOne
    @MapsId("tripID")
    @JoinColumn(name = "tripID")
    private @Getter @Setter
    Trip trip;
    private @Getter @Setter float rating;
    private @Getter @Setter String text;

    @Enumerated(EnumType.STRING)
    private @Getter @Setter RatingType type;

}
