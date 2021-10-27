package nl.fontys.withdrive.interfaces.services;


import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ITripManager {
    boolean Add(TripRequestDTO trip);

    List<TripResponseDTO> RetrieveAll();

    TripResponseDTO RetrieveByNumber(UUID number);

    boolean Update(TripRequestDTO trip);

    boolean Delete(UUID number);
}
