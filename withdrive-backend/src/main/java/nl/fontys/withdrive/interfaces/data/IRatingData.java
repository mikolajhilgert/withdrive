package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.entity.Rating;

import java.util.List;
import java.util.UUID;

public interface IRatingData {
    void add(Rating rating);
    Rating getRatingByUserAndTrip(UUID user, UUID trip);
    Float averageRatingUser(UUID user);
    List<Rating> getRatingsPerUser(UUID user);
}
