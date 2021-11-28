package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.enumeration.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.UUID;

public interface IJPATripData extends JpaRepository<Trip, UUID> {
    Trip getFirstByTripID(UUID ID);
    List<Trip> getTripsByStatus(TripStatus status);
    List<Trip> getTripsByOriginAndStatusIs(String origin,String status);
    @Query(value = "select t.* from trips as t left outer join applications as ap on t.id = ap.tripid where t.status=\"AWAITING\" and ap.userid = ?1",nativeQuery = true)
    List<Trip> getActiveTripsByUser(String ID);
    @Query(value = "select t.* from trips as t left outer join applications as ap on t.id = ap.tripid where t.status=\"AWAITING\" and t.driver_id = ?1",nativeQuery = true)
    List<Trip> getActiveTripsByDriver(String ID);
}
