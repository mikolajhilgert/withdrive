package nl.fontys.withdrive.repository.jpa;

import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.jpa.IJPATripData;
import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
    public boolean Create(TripDTO trip) {
        TripVM temp = new TripVM(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),trip.getDriver().getClientNumber(), Collections.emptyList());
        db.save(temp);
        return true;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        //return db.findAll();
        return null;
    }

    @Override
    public TripDTO RetrieveByNumber(UUID number) {
        return null;
    }

    @Override
    public boolean Update(TripDTO trip) {
        return false;
    }

    @Override
    public boolean Delete(UUID number) {
        return false;
    }
}
