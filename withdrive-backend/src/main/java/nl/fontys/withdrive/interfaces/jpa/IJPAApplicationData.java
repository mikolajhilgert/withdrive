package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.TripApplicationKEY;
import nl.fontys.withdrive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IJPAApplicationData extends JpaRepository<TripApplication, UUID> {
    //TripApplication getTripApplicationByApplicantAndTrip(User user, Trip trip);
}
