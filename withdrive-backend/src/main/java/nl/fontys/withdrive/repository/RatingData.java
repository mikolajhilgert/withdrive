package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.Rating;
import nl.fontys.withdrive.interfaces.data.IRatingData;
import nl.fontys.withdrive.interfaces.jpa.IJPARatingData;
import nl.fontys.withdrive.interfaces.jpa.IJPATripData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository @Transactional
public class RatingData implements IRatingData {
    private final IJPARatingData db;

    @Autowired
    public RatingData(IJPARatingData db){
        this.db = db;
    }

    @Override
    public void add(Rating rating) {
        db.save(rating);
    }

    @Override
    public Rating getRatingByUserAndTrip(UUID user, UUID trip) {
        return null;
    }

    @Override
    public Float averageRatingUser(UUID user) {
        if(db.averageRatingUser(user.toString())==null){
            return 0.0f;
        }
        return db.averageRatingUser(user.toString());
    }

    @Override
    public List<Rating> getRatingsPerUser(UUID user) {
        return db.getRatingPerUser(user.toString());
    }

    @Override
    public boolean hasReviewed(UUID trip, UUID user) {
        Integer count = db.hasReviewed(trip.toString(),user.toString());
        if(count == 0){
            return false;
        }
        return true;
    }
}
