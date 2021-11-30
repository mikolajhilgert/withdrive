package nl.fontys.withdrive.interfaces.service;


import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.entity.Trip;

import java.util.List;
import java.util.UUID;

public interface ITripService {
    boolean Add(TripRequestDTO trip);

    List<TripResponseDTO> RetrieveAll();

    List<TripResponseDTO> retrieveActiveTrips();

    List<TripResponseDTO> retrieveActiveTripsByUser(UUID id);
    List<TripResponseDTO> retrieveActiveTripsByDriver(UUID id);

    List<TripResponseDTO> retrieveTripsByUser(UUID id);

    List<TripResponseDTO> retrieveTripsByDriver(UUID id);

    TripResponseDTO RetrieveByNumber(UUID number);

    boolean Update(TripRequestDTO trip);

    boolean Delete(UUID tripID,UUID userID);

}
