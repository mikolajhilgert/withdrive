package nl.fontys.withdrive.interfaces.converter;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;

import java.util.List;

public interface IApplicationConverter {
    TripApplication RequestDTOToEntity(ApplicationRequestDTO application);
    ApplicationRequestDTO EntityToRequestDTO(TripApplication application);
    List<ApplicationResponseDTO> ListEntityToResponseDTO(List<TripApplication> applications);
}
