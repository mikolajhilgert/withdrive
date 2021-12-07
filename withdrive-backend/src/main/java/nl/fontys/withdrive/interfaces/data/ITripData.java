package nl.fontys.withdrive.interfaces.data;


import nl.fontys.withdrive.entity.Trip;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ITripData {
    void Create(Trip trip);

    List<Trip> RetrieveAll();

    Trip RetrieveByNumber(UUID number);

    void Update(Trip trip);

    void Delete(UUID number);

    List<Trip> retrieveActiveTrips();

    Page<Trip> retrieveActiveAsPage(Integer page);

    List<Trip> retrieveActiveTripsByUser(UUID id);

    List<Trip> retrieveActiveTripsByDriver(UUID id);

    List<Trip> retrieveTripsByUser(UUID id);

    List<Trip> retrieveTripsByDriver(UUID id);

    List<Trip> retrieveActiveTripsByOrigin(String origin);

    void lockTrip(UUID id);
}