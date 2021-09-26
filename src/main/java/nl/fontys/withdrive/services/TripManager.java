package nl.fontys.withdrive.services;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.services.ITripManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripManager implements ITripManager {
    private final ITripData saved;

    public TripManager(ITripData saved){
        this.saved = saved;
    }

    @Override
    public boolean Add(TripDTO trip) {
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
    public boolean Update(TripDTO trip) {
        return false;
    }

    @Override
    public boolean Delete(int number) {
        return false;
    }
}
