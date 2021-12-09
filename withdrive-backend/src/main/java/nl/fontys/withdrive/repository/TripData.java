package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.jpa.IJPATripData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository @Transactional
public class TripData implements ITripData {
    private final IJPATripData db;

    @Autowired
    public TripData(IJPATripData db){
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
        return db.getTripsByStatus(TripStatus.OPEN);
    }

    @Override
    public Page<Trip> retrieveActiveAsPage(Integer page) {
        Pageable pageableRequest = PageRequest.of(page, 5);
        return db.getActiveTripsPage(pageableRequest);
    }

    @Override
    public Integer retrieveActiveTripsCount() {
        return db.retrieveActiveTripsCount();
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
    public List<Trip> retrieveTripsByUser(UUID id) {
        return db.getTripsByUser(id.toString());
    }

    @Override
    public List<Trip> retrieveTripsByDriver(UUID id) {
        return db.getTripsByDriver(id.toString());
    }

    @Override
    public List<Trip> retrieveActiveTripsByOrigin(String origin) {
        return db.getTripsByOriginAndStatusIs(origin,TripStatus.OPEN);
    }

    @Override
    public void lockTrip(UUID id) {
        db.lockTrip(id);
    }
}
