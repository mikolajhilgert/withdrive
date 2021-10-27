package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripConverter implements ITripConverter {
    @Override
    public Trip RequestDTOToEntity(TripRequestDTO trip) {
        return null;
    }

    @Override
    public TripResponseDTO EntityToResponseDTO(Trip trip) {
        return null;
    }

    @Override
    public List<TripResponseDTO> ListEntityToResponseDTO(List<Trip> trip) {
        return null;
    }
}
