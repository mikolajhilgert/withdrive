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
    //  trips.add(new TripDTO(0));
    }

    @Override
    public boolean Create(TripDTO user) {
        return false;
    }

    @Override
    public List<TripDTO> RetrieveAll() {
        return null;
    }

    @Override
    public TripDTO RetrieveByNumber(int number) {
        return null;
    }

    @Override
    public boolean Update(TripDTO client) {
        return false;
    }

    @Override
    public boolean Delete(int number) {
        return false;
    }
}
