package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.IApplicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationManager implements IApplicationManager {
    private final IApplicationData saved;
    private final IUserData users;
    private final ITripData trips;
    private final IApplicationConverter applicationConverter;

    @Autowired
    public ApplicationManager(IApplicationData saved, IUserData users,ITripData trips, IApplicationConverter applicationConverter){
        this.saved = saved;
        this.users = users;
        this.trips = trips;
        this.applicationConverter = applicationConverter;
    }

    @Override
    public void Add(ApplicationRequestDTO application) {
        saved.Create(applicationConverter.RequestDTOToEntity(application));
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByTripID(UUID id) {
        return applicationConverter.ListEntityToResponseDTO(saved.RetrieveByTripID(id));
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByUserID(UUID id) {
        return applicationConverter.ListEntityToResponseDTO(saved.RetrieveByUserID(id));
    }

    @Override
    public void Update(ApplicationRequestDTO application) {

    }

    @Override
    public void Delete(UUID id) {

    }
}
