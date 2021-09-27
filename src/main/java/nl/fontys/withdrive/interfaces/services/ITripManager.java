package nl.fontys.withdrive.interfaces.services;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;

import java.util.List;

public interface ITripManager {
    boolean Add(TripVM trip);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(int number);

    boolean Update(TripVM trip);

    boolean Delete(int number);

}
