package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;

import java.util.List;
import java.util.UUID;

public interface ITripData {
    void Create(TripVM trip);

    List<TripVM> RetrieveAll();

    TripVM RetrieveByNumber(UUID number);

    void Update(TripVM trip);

    void Delete(UUID number);

}