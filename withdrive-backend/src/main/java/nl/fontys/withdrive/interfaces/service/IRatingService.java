package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.dto.rating.AnonymousRatingDTO;
import nl.fontys.withdrive.dto.rating.RatingDTO;

import java.util.List;
import java.util.UUID;

public interface IRatingService {
    void add(RatingDTO rating);
    RatingDTO getRatingByUserAndTrip(UUID user, UUID trip);
    float averageRatingUser(UUID user);
    List<AnonymousRatingDTO> getRatingsPerUser(UUID user);
}
