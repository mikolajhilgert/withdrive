package nl.fontys.withdrive.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private @Getter @Setter UUID rater;
    private @Getter @Setter UUID user;
    private @Getter @Setter UUID trip ;
    private @Getter @Setter float rating;
    private @Getter @Setter String text;
}
