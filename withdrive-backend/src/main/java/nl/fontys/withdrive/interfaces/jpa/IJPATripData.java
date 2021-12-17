package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.enumeration.TripStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IJPATripData extends JpaRepository<Trip, UUID> {
    Trip getFirstByTripID(UUID ID);
    List<Trip> getTripsByStatus(TripStatus status);
    List<Trip> getTripsByOriginAndStatusIs(String origin,TripStatus status);

    @Query(value = "select * from trips where status=\"OPEN\"",nativeQuery = true)
    Page<Trip> getActiveTripsPage(Pageable pageable);

    @Query(value = "select count(*) from trips where status=\"OPEN\"",nativeQuery = true)
    Integer retrieveActiveTripsCount();

    @Query(value = "select t.* from trips as t inner join applications as ap on t.id = ap.tripid where t.status=\"OPEN\" and ap.userid = ?1",nativeQuery = true)
    List<Trip> getActiveTripsByUser(String ID);

    @Query(value = "select t.* from trips as t where t.status=\"OPEN\" and t.driver_id = ?1",nativeQuery = true)
    List<Trip> getActiveTripsByDriver(String ID);

    @Query(value = "select t.* from trips as t inner join applications as ap on t.id = ap.tripid and ap.userid = ?1 where t.status=\"LOCKED\"",nativeQuery = true)
    List<Trip> getTripsByUser(String ID);

    @Query(value = "select t.* from trips as t where t.driver_id = ?1 and t.status=\"LOCKED\"",nativeQuery = true)
    List<Trip> getTripsByDriver(String ID);

    @Modifying
    @Query("update Trip as t set t.status = 'LOCKED' where t.tripID = ?1")
    void lockTrip(UUID id);

//    @Modifying
//    @Query("UPDATE trips SET status = 'LOCKED' WHERE id = ?1")
//    void lockTrip(String id);
}
