package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Rating;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IJPARatingData extends JpaRepository<Rating, UUID> {
//    Rating findByRaterAndTrip(User user, Trip trip);
    @Query(value = "SELECT AVG(rating) FROM ratings WHERE userid= :userID and type=\"PASSENGER\"",nativeQuery = true)
    float averageRatingUser(@Param("userID") String userID);
}
