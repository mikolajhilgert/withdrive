package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.TripApplicationKEY;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IJPAApplicationData extends JpaRepository<TripApplication, UUID> {
}
