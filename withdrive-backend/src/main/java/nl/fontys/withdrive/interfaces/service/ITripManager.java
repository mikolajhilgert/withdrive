package nl.fontys.withdrive.interfaces.service;


import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ITripManager {
    boolean Add(TripRequestDTO trip);

    List<TripResponseDTO> RetrieveAll();

    List<TripResponseDTO> retrieveActiveTrips();

    List<TripResponseDTO> retrieveTripsByUser(UUID id);
    List<TripResponseDTO> retrieveTripsByDriver(UUID id);

    TripResponseDTO RetrieveByNumber(UUID number);

    boolean Update(TripRequestDTO trip);

    boolean Delete(UUID number);
}
