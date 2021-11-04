package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripConverter implements ITripConverter {

    private final ModelMapper mapper;
    private final IUserData users;
    private final IApplicationData apps;

    public TripConverter(ModelMapper mapper, IUserData users, IApplicationData apps){
        this.mapper = mapper;
        this.users = users;
        this.apps = apps;
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
        output.setDriver(mapper.map(users.RetrieveByID(trip.getDriver().getUserID()),UserDTO.class));
        output.setPassengers(ExtractPassengers(apps.RetrieveByTripID(trip.getTripID())));
        return output;
    }

    @Override
    public List<TripResponseDTO> ListEntityToResponseDTO(List<Trip> trips) {
        List<TripResponseDTO> output = new ArrayList<>();
        for(Trip trip : trips){
            TripResponseDTO temp = mapper.map(trip,TripResponseDTO.class);
            temp.setDriver(mapper.map(users.RetrieveByID(trip.getDriver().getUserID()),UserDTO.class));
            temp.setPassengers(ExtractPassengers(apps.RetrieveByTripID(trip.getTripID())));
            output.add(temp);
        }
        return output;
    }

    private List<UserDTO> ExtractPassengers(List<TripApplication> apps){
        List<UserDTO> passengers = new ArrayList<>();
        for(TripApplication app : apps){
            if(app.getStatus().equals(ApplicationStatus.ACCEPTED)){
                passengers.add(mapper.map(users.RetrieveByID(app.getApplicant().getUserID()),UserDTO.class));
            }
        }
        return passengers;
    }
}
