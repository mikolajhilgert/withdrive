package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.model.dto.TripDTO;

import java.util.List;
import java.util.UUID;

public interface ITripData {
    boolean Create(TripDTO trip);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(UUID number);

    boolean Update(TripDTO trip);

    boolean Delete(UUID number);

}