package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;

import java.util.List;
import java.util.UUID;

public interface IApplicationData {
    void Create(TripApplication application);

    List<TripApplication> RetrieveByTripID(UUID id);

    List<TripApplication> RetrieveByUserID(UUID id);

    void Update(TripApplication application);

    void Delete(UUID id);

    TripApplication RetrieveByUserIDAndTripID(UUID uID, UUID tID);
}
