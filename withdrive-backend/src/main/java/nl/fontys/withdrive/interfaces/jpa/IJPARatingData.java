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
    @Query(value = "SELECT AVG(rating) FROM ratings WHERE userid= :userID",nativeQuery = true)
    Float averageRatingUser(@Param("userID") String userID);
    @Query(value = "SELECT count(rating) from ratings where raterid= :userID and tripid = :tripID",nativeQuery = true)
    Integer hasReviewed(@Param("tripID") String tripID,@Param("userID") String userID);
    @Query(value="SELECT * from ratings where userid=:userID",nativeQuery = true)
    List<Rating> getRatingPerUser(@Param("userID") String userID);
}
