package nl.fontys.withdrive.interfaces.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.entity.Trip;

import java.util.List;

public interface ITripConverter {
    Trip RequestDTOToEntity(TripRequestDTO trip);
    TripResponseDTO EntityToResponseDTO(Trip trip);
    List<TripResponseDTO> ListEntityToResponseDTO(List<Trip> trip);
}
