package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJPATripData extends JpaRepository<Trip, UUID> {
    Trip getFirstByTripID(UUID ID);
}
