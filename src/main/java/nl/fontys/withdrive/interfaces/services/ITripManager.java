package nl.fontys.withdrive.interfaces.services;

import nl.fontys.withdrive.dto.TripDTO;

import java.util.List;

public interface ITripManager {
    boolean Add(TripDTO trip);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(int number);

    boolean Update(TripDTO trip);

    boolean Delete(int number);

}
