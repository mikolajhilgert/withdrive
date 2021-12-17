package nl.fontys.withdrive.interfaces.converter;

import nl.fontys.withdrive.dto.rating.AnonymousRatingDTO;
import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.entity.Rating;

import java.util.List;

public interface IRatingConverter {
    Rating DTOToEntity(RatingDTO rating);
    List<AnonymousRatingDTO> ListEntityToAnonymousDTO(List<Rating> ratings);
    RatingDTO EntityToDTO(Rating rating);
}
