package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJPATripData extends JpaRepository<TripVM, UUID> {
    //TripDTO getFirstByTripID(UUID ID);
}
