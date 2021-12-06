package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserMiniDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Trip output = mapper.map(trip,Trip.class);
        output.setDriver(users.RetrieveByID(trip.getDriver()));
        return output;
    }

    @Override
    public TripResponseDTO EntityToResponseDTO(Trip trip) {
        TripResponseDTO output = mapper.map(trip,TripResponseDTO.class);
        output.setDriver(mapper.map(users.RetrieveByID(trip.getDriver().getUserID()), UserMiniDTO.class));
        output.setPassengers(ExtractPassengers(trip));
        return output;
    }

    @Override
    public List<TripResponseDTO> ListEntityToResponseDTO(List<Trip> trips) {
        List<TripResponseDTO> output = new ArrayList<>();
        List<UserMiniDTO> drivers = users.GetDrivers().stream().map(x->mapper.map(x, UserMiniDTO.class)).collect(Collectors.toList());
        for(Trip trip : trips){
            TripResponseDTO temp = mapper.map(trip,TripResponseDTO.class);
            for(UserMiniDTO driver : drivers){
                if(driver.getUserID() == trip.getTripID()){
                    temp.setDriver(driver);
                    break;
                }
            }
            temp.setPassengers(ExtractPassengers(trip));
            output.add(temp);
        }
        return output;
    }

    private List<UserMiniDTO> ExtractPassengers(Trip trip){
        List<UserMiniDTO> pass;
        pass = users.RetrieveUsersByTripID(trip.getTripID()).stream().map(x->mapper.map(x, UserMiniDTO.class)).collect(Collectors.toList());
        return pass;
    }
}
