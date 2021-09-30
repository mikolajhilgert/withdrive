package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;

import java.util.List;

public interface ITripData {
    boolean Create(TripDTO trip);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(int number);

    boolean Update(TripDTO trip);

    boolean Delete(int number);

}