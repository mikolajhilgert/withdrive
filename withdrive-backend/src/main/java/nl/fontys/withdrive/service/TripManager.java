package nl.fontys.withdrive.service;

import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.dto.UserDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;
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
        saved.Create(trip);
        return true;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        return GetTripVMToDTO(saved.RetrieveAll());
    }

    @Override
    public TripDTO RetrieveByNumber(UUID number) {
        List<TripVM> temp = new ArrayList<>();
        temp.add(saved.RetrieveByNumber(number));
        return GetTripVMToDTO(temp).get(0);
    }   

    @Override
    public boolean Update(TripVM trip) {
        saved.Create(trip);
        return true;
    }

    @Override
    public boolean Delete(UUID number) {
        saved.Delete(number);
        return true;
    }

    private List<TripDTO> GetTripVMToDTO(List<TripVM> input){
        List<TripDTO> temp = new ArrayList<>();
        for(TripVM vm : input){
            List<UserDTO> passengers = new ArrayList<>();
            if(vm.getPassengers() != null){
                for(UUID pass : vm.getPassengers()){
                    passengers.add(users.RetrieveByNumber(pass));
                }
            }
            temp.add(new TripDTO(vm.getTripID(),vm.getOrigin(),vm.getDestination(),vm.getDescription(),users.RetrieveByNumber(vm.getDriver()),passengers));
        }
        return temp;
    }

//    private List<UserDTO> ConvertPassengerIDToObject(List<UUID> passengerList){
//        List<UserDTO> passengers = new ArrayList<>();
//        for (UUID passengerID :passengerList){
//            passengers.add(users.RetrieveByNumber(passengerID));
//        }
//        return passengers;
//    }
//
//    private boolean DriverCheck(UserDTO driver, List<UserDTO> passengers){
//        for(UserDTO p : passengers){
//            if(p == driver){
//                return false;
//            }
//        }
//        return users.RetrieveAll().contains(driver);
//    }

//    private boolean UniqueIDCheck(UUID id){
//        for(TripDTO trips : saved.RetrieveAll()){
//            if(trips.getTripID().equals(id)){
//                return false;
//            }
//        }
//        return true;
//    }
}
