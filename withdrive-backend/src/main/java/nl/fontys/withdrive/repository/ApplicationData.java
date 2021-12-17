package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.jpa.IJPAApplicationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ApplicationData implements IApplicationData {

    private final IJPAApplicationData db;

    @Autowired
    public ApplicationData(IJPAApplicationData db){
        this.db = db;
    }

    @Override
    public void Create(TripApplication application) {
        db.save(application);
    }

    @Override
    public List<TripApplication> RetrieveByTripID(UUID id) {
        List<TripApplication> output = new ArrayList<>();
        for(TripApplication app: db.findAll()){
            if(app.getTrip().getTripID().equals(id)){
                output.add(app);
            }
        }
        return output;
    }

    @Override
    public List<TripApplication> RetrieveByUserID(UUID id) {
        List<TripApplication> output = new ArrayList<>();
        for(TripApplication app: db.findAll()){
            if(app.getApplicant().getUserID().equals(id)){
                output.add(app);
            }
        }
        return output;
    }

    @Override
    public void Update(TripApplication application) {
        TripApplication toUpdate = db.getTripApplicationByApplicantAndTrip(application.getApplicant(),application.getTrip());
        toUpdate.setApplicant(application.getApplicant());
        toUpdate.setTrip(application.getTrip());
        toUpdate.setStatus(application.getStatus());
        toUpdate.setText(application.getText());
        db.save(toUpdate);
    }

    @Override
    public void Delete(UUID id) {

    }

    @Override
    public TripApplication RetrieveByUserIDAndTripID(UUID uID, UUID tID) {
        for(TripApplication app: RetrieveByTripID(tID)){
            if(app.getApplicant().getUserID().equals(uID)){
                return app;
            }
        }
        return null;
    }
}
