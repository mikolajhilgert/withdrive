package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.jpa.IJPATripData;
import nl.fontys.withdrive.model.viewmodel.TripVM;
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
    public void Create(TripVM trip) {
        db.save(trip);
    }

    @Override
    public List<TripVM> RetrieveAll() {
        return db.findAll();
    }

    @Override
    public TripVM RetrieveByNumber(UUID number) {
        return db.getFirstByTripID(number);
    }

    @Override
    public void Update(TripVM trip) {
        TripVM toUpdate = RetrieveByNumber(trip.getTripID());
        toUpdate.setDescription(trip.getDescription());
        toUpdate.setDestination(trip.getDestination());
        toUpdate.setOrigin(trip.getOrigin());
        toUpdate.setDriver(trip.getDriver());
        toUpdate.setPassengers(trip.getPassengers());
        db.save(toUpdate);
    }

    @Override
    public void Delete(UUID number) {
        db.deleteById(number);
    }
}
