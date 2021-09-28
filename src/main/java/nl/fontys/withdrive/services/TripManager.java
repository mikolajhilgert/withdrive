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
        if(DriverIsNotAPassenger(driver,passengers)){
            return saved.Create(new TripDTO(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),driver,passengers));
        }
        return false;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        return saved.RetrieveAll();
    }

    @Override
    public TripDTO RetrieveByNumber(int number) {
        return saved.RetrieveByNumber(number);
    }

    @Override
    public boolean Update(TripVM trip) {
        UserDTO driver = users.RetrieveByNumber(trip.getDriver());
        List<UserDTO> passengers = ConvertPassengerIDToObject(trip.getPassengers());
        if(DriverIsNotAPassenger(driver,passengers)){
            return saved.Update(new TripDTO(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),driver,passengers));
        }
        return false;
    }

    @Override
    public boolean Delete(int number) {
        return saved.Delete(number);
    }

    private List<UserDTO> ConvertPassengerIDToObject(List<Integer> passengerList){
        List<UserDTO> passengers = new ArrayList<>();
        for (int passengerID :passengerList){
            passengers.add(users.RetrieveByNumber(passengerID));
        }
        return passengers;
    }

    private boolean DriverIsNotAPassenger(UserDTO driver, List<UserDTO> passengers){
        for(UserDTO p : passengers){
            if(p == driver){
                return false;
            }
        }
        return true;
    }
}
