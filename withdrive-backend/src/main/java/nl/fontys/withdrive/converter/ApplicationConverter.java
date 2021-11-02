package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConverter implements IApplicationConverter {
    private final ModelMapper mapper;
    private final IUserData users;
    private final ITripData trips;

    public ApplicationConverter(ModelMapper mapper, IUserData users, ITripData trips){
        this.mapper = mapper;
        this.users = users;
        this.trips = trips;
    }

    @Override
    public TripApplication RequestDTOToEntity(ApplicationRequestDTO application) {
        TripApplication output = mapper.map(application, TripApplication.class);
        output.setTrip(trips.RetrieveByNumber(application.getTrip()));
        output.setApplicant(users.RetrieveByID(application.getUser()));
        return output;
//        return mapper.map(application, TripApplication.class);
    }

    @Override
    public List<ApplicationResponseDTO> ListEntityToResponseDTO(List<TripApplication> application) {
        List<ApplicationResponseDTO> output = new ArrayList<>();
        for(TripApplication app : application){
            ApplicationResponseDTO temp = mapper.map(app, ApplicationResponseDTO.class);
//            temp.setUser(app.getApplicant().getUserID());
            temp.setUser(mapper.map(users.RetrieveByID(app.getApplicant().getUserID()), UserDTO.class));
            output.add(temp);
        }
        return output;
    }
}
