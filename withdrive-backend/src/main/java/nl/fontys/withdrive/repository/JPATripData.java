package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.jpa.IJPATripData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JPATripData implements ITripData {
    private final IJPATripData db;

    @Autowired
    public JPATripData(IJPATripData db){
        this.db = db;
    }

    @Override
    public void Create(Trip trip) {
        db.save(trip);
    }

    @Override
    public List<Trip> RetrieveAll() {
        return db.findAll();
    }

    @Override
    public Trip RetrieveByNumber(UUID number) {
        return db.getFirstByTripID(number);
    }

    @Override
    public void Update(Trip trip) {
        Trip toUpdate = RetrieveByNumber(trip.getTripID());
        toUpdate.setTripID(trip.getTripID());
        toUpdate.setDescription(trip.getDescription());
        toUpdate.setDestination(trip.getDestination());
        toUpdate.setDate(trip.getDate());
        toUpdate.setMaxPassengers(trip.getMaxPassengers());
        toUpdate.setLicensePlate(trip.getLicensePlate());
        toUpdate.setPricePerPassenger(trip.getPricePerPassenger());
        toUpdate.setOrigin(trip.getOrigin());
        db.save(toUpdate);
    }

    @Override
    public void Delete(UUID number) {
        db.deleteById(number);
    }

    @Override
    public List<Trip> retrieveActiveTrips() {
        return db.getTripsByStatus(TripStatus.AWAITING);
    }

    @Override
    public List<Trip> retrieveActiveTripsByUser(UUID id) {
        return db.getActiveTripsByUser(id.toString());
    }

    @Override
    public List<Trip> retrieveActiveTripsByDriver(UUID id) {
        return db.getActiveTripsByDriver(id.toString());
    }

    @Override
    public List<Trip> retrieveActiveTripsByOrigin(String origin) {
        return db.getTripsByOriginAndStatusIs(origin,"AWAITING");
    }
}
