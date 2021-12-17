package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.rating.AnonymousRatingDTO;
import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.entity.Rating;
import nl.fontys.withdrive.interfaces.converter.IRatingConverter;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RatingConverter implements IRatingConverter {
    private final ModelMapper mapper;
    private final ITripData trips;
    private final IUserData users;

    public RatingConverter(ModelMapper mapper, ITripData trips,IUserData users){
        this.mapper = mapper;
        this.trips = trips;
        this.users = users;
    }

    @Override
    public Rating DTOToEntity(RatingDTO rating) {
        Rating output = mapper.map(rating, Rating.class);
        output.setTrip(trips.RetrieveByNumber(rating.getTrip()));
        output.setRater(users.RetrieveByID(rating.getRater()));
        output.setUser(users.RetrieveByID(rating.getUser()));
        return output;
    }

    @Override
    public List<AnonymousRatingDTO> ListEntityToAnonymousDTO(List<Rating> ratings) {
        List<AnonymousRatingDTO> output = new ArrayList<>();
        for(Rating rating : ratings){
            output.add(new AnonymousRatingDTO(UUID.randomUUID(),rating.getRater().getFirstName(),rating.getRating(),rating.getText()));
        }
        return output;
    }

    @Override
    public RatingDTO EntityToDTO(Rating rating) {
        RatingDTO output = mapper.map(rating, RatingDTO.class);
        output.setTrip(rating.getTrip().getTripID());
        output.setRater(rating.getRater().getUserID());
        output.setUser(rating.getUser().getUserID());
        return output;
    }
}
