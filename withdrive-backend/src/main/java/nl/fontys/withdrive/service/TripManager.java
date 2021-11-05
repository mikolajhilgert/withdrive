package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.ITripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TripManager implements ITripManager {
    private final ITripData saved;
    private final IUserData users;
    private final ITripConverter tripConverter;

    @Autowired
    public TripManager(ITripData saved, IUserData users, ITripConverter tripConverter){
        this.saved = saved;
        this.users = users;
        this.tripConverter = tripConverter;
    }

    @Override
    public boolean Add(TripRequestDTO trip) {
        saved.Create(tripConverter.RequestDTOToEntity(trip));
        return true;
    }

    @Override
    public List<TripResponseDTO> RetrieveAll() {
        return tripConverter.ListEntityToResponseDTO(saved.RetrieveAll());
    }

    @Override
    public TripResponseDTO RetrieveByNumber(UUID number) {
        List<Trip> temp = new ArrayList<>();
        temp.add(saved.RetrieveByNumber(number));
        if(temp.get(0) != null){
            return tripConverter.EntityToResponseDTO((temp).get(0));
        }
        return null;
    }

    @Override
    public boolean Update(TripRequestDTO trip) {
        saved.Create(tripConverter.RequestDTOToEntity(trip));
        return true;
    }

    @Override
    public boolean Delete(UUID number) {
        saved.Delete(number);
        return true;
    }

}
