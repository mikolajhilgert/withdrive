package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripConverter implements ITripConverter {

    private final ModelMapper mapper;
    private final IUserData users;

    public TripConverter(ModelMapper mapper, IUserData users){
        this.mapper = mapper;
        this.users = users;
    }

    @Override
    public Trip RequestDTOToEntity(TripRequestDTO trip) {
        return mapper.map(trip,Trip.class);
    }

    @Override
    public TripResponseDTO EntityToResponseDTO(Trip trip) {
        TripResponseDTO output = mapper.map(trip,TripResponseDTO.class);
        output.setDriver(mapper.map(users.RetrieveByID(trip.getDriver()),UserDTO.class));
        return output;
    }

    @Override
    public List<TripResponseDTO> ListEntityToResponseDTO(List<Trip> trips) {
        List<TripResponseDTO> output = new ArrayList<>();
        for(Trip trip : trips){
            TripResponseDTO temp = mapper.map(trip,TripResponseDTO.class);
            temp.setDriver(mapper.map(users.RetrieveByID(trip.getDriver()),UserDTO.class));
            output.add(temp);
        }
        return output;
    }
}
