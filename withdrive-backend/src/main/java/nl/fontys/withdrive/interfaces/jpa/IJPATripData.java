package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.enumeration.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.UUID;

public interface IJPATripData extends JpaRepository<Trip, UUID> {
    Trip getFirstByTripID(UUID ID);
    List<Trip> getTripsByStatus(TripStatus status);
}
