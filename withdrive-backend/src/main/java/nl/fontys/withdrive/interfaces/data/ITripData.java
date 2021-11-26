package nl.fontys.withdrive.interfaces.data;


import nl.fontys.withdrive.entity.Trip;

import java.util.List;
import java.util.UUID;

public interface ITripData {
    void Create(Trip trip);

    List<Trip> RetrieveAll();

    Trip RetrieveByNumber(UUID number);

    void Update(Trip trip);

    void Delete(UUID number);

    List<Trip> retrieveActiveTrips();

    List<Trip> retrieveActiveTripsByUser(UUID id);

    List<Trip> retrieveActiveTripsByDriver(UUID id);
}