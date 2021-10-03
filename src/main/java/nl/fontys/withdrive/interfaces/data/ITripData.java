package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;

import java.util.List;
import java.util.UUID;

public interface ITripData {
    boolean Create(TripDTO trip);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(UUID number);

    boolean Update(TripDTO trip);

    boolean Delete(UUID number);

}