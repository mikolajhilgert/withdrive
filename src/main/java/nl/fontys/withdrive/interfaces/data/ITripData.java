package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.dto.TripDTO;

import java.util.List;

public interface ITripData {
    boolean Create(TripDTO user);

    List<TripDTO> RetrieveAll();

    TripDTO RetrieveByNumber(int number);

    boolean Update(TripDTO client);

    boolean Delete(int number);

}