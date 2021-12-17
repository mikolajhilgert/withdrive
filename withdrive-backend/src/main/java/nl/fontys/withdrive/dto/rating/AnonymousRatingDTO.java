package nl.fontys.withdrive.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class AnonymousRatingDTO {
    private @Getter @Setter UUID id = UUID.randomUUID();
    private @Getter @Setter String name;
    private @Getter @Setter float rating;
    private @Getter @Setter String text;
}
