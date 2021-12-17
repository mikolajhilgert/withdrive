package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.rating.AnonymousRatingDTO;
import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.converter.IRatingConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.IRatingData;
import nl.fontys.withdrive.interfaces.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService implements IRatingService {
    private final IRatingData saved;
    private final IRatingConverter ratingConverter;

    @Autowired
    public RatingService(IRatingData saved, IRatingConverter ratingConverter){
        this.saved = saved;
        this.ratingConverter = ratingConverter;
    }

    @Override
    public void add(RatingDTO rating) {
        saved.add(ratingConverter.DTOToEntity(rating));
    }

    @Override
    public RatingDTO getRatingByUserAndTrip(UUID user, UUID trip) {
        return null;
    }

    @Override
    public Float averageRatingUser(UUID user) {
        return saved.averageRatingUser(user);
    }

    @Override
    public List<AnonymousRatingDTO> getRatingsPerUser(UUID user) {
        return null;
    }

    @Override
    public boolean hasReviewed(UUID trip, UUID user) {
        return saved.hasReviewed(trip,user);
    }
}
