package nl.fontys.withdrive.services;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.services.ITripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TripManager implements ITripManager {
    private final ITripData saved;
    private final IUserData users;

    @Autowired
    public TripManager(ITripData saved, IUserData users){
        this.saved = saved;
        this.users = users;
    }

    @Override
    public boolean Add(TripVM trip) {
        UserDTO driver = users.RetrieveByNumber(trip.getDriver());
        List<UserDTO> passengers = ConvertPassengerIDToObject(trip.getPassengers());
        if(DriverCheck(driver,passengers) && UniqueIDCheck(trip.getTripID())){
            return saved.Create(new TripDTO(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),driver,passengers));
        }
        return false;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        return saved.RetrieveAll();
    }

    @Override
    public TripDTO RetrieveByNumber(UUID number) {
        return saved.RetrieveByNumber(number);
    }   

    @Override
    public boolean Update(TripVM trip) {
        UserDTO driver = users.RetrieveByNumber(trip.getDriver());
        List<UserDTO> passengers = ConvertPassengerIDToObject(trip.getPassengers());
        if(DriverCheck(driver,passengers)){
            return saved.Update(new TripDTO(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),driver,passengers));
        }
        return false;
    }

    @Override
    public boolean Delete(UUID number) {
        return saved.Delete(number);
    }

    private List<UserDTO> ConvertPassengerIDToObject(List<UUID> passengerList){
        List<UserDTO> passengers = new ArrayList<>();
        for (UUID passengerID :passengerList){
            passengers.add(users.RetrieveByNumber(passengerID));
        }
        return passengers;
    }

    private boolean DriverCheck(UserDTO driver, List<UserDTO> passengers){
        for(UserDTO p : passengers){
            if(p == driver){
                return false;
            }
        }
        return users.RetrieveAll().contains(driver);
    }

    private boolean UniqueIDCheck(UUID id){
        for(TripDTO trips : saved.RetrieveAll()){
            if(trips.getTripID().equals(id)){
                return false;
            }
        }
        return true;
    }
}
