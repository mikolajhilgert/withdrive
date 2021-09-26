package nl.fontys.withdrive.repositories;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.data.ITripData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeTripData implements ITripData {
    private final List<TripDTO> trips;

    public FakeTripData() {
        trips = new ArrayList<>();
        trips.add(new TripDTO(0,"Prague","Brno","Quick drive",new UserDTO(0, "JohnDoe@example.com", "John", "Doe", "10-05-2002", "Male", "+42060605797", "qwerty"),null));
    }

    @Override
    public boolean Create(TripDTO trip) {
        if(this.RetrieveByNumber(trip.getTripID()) == null){
            return this.trips.add(trip);
        }
        return false;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        return this.trips;
    }

    @Override
    public TripDTO RetrieveByNumber(int number) {
        for (TripDTO temp : trips)
        {
            if(temp.getTripID() == number){
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean Update(TripDTO trip) {
        TripDTO temp = RetrieveByNumber(trip.getTripID());
        if(temp != null){
            return this.trips.set(this.trips.indexOf(temp),trip) != null;
        }
        return false;
    }

    @Override
    public boolean Delete(int number) {
        TripDTO temp = RetrieveByNumber(number);
        if(temp != null){
            return this.trips.remove(temp);
        }
        return false;
    }
}
