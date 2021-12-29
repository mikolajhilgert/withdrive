package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import nl.fontys.withdrive.interfaces.service.IEmailService;
import nl.fontys.withdrive.interfaces.service.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService implements IApplicationService {
    private final IApplicationData saved;
    private final IApplicationConverter applicationConverter;
    private final IEmailService mailer;

    @Autowired
    public ApplicationService(IApplicationData saved, IApplicationConverter applicationConverter, IEmailService mailer){
        this.saved = saved;
        this.applicationConverter = applicationConverter;
        this.mailer = mailer;
    }

    @Override
    public void Add(ApplicationRequestDTO application, UUID user) {
        if(application.getStatus().equals(ApplicationStatus.PENDING)){
            application.setUser(user);
            TripApplication app = applicationConverter.RequestDTOToEntity(application);
            if(app.getTrip().getDriver().getUserID() != user){
                saved.Create(app);
            }
            mailer.sendApplicationNotification(app.getTrip().getDriver().getEmail());
        }
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByTripID(UUID id) {
        return applicationConverter.ListEntityToResponseDTO(saved.RetrieveByTripID(id));
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveByUserID(UUID id) {
        List<ApplicationResponseDTO> temp = new ArrayList<>();
        for(ApplicationResponseDTO app : applicationConverter.ListEntityToResponseDTO(saved.RetrieveByUserID(id))){
            if(app.getTrip().getStatus() == TripStatus.LOCKED){
                temp.add(app);
            }
        }
        return temp;
    }

    @Override
    public List<ApplicationResponseDTO> RetrieveActiveByUserID(UUID id) {
        List<ApplicationResponseDTO> temp = new ArrayList<>();
        for(ApplicationResponseDTO app : applicationConverter.ListEntityToResponseDTO(saved.RetrieveByUserID(id))){
            if(app.getTrip().getStatus() == TripStatus.OPEN){
                temp.add(app);
            }
        }
        return temp;
    }

    @Override
    public ApplicationRequestDTO RetrieveByUserIDAndTripID(UUID uID, UUID tID) {
        return applicationConverter.EntityToRequestDTO(saved.RetrieveByUserIDAndTripID(uID,tID));
    }

    @Override
    public void RespondToApplication(ApplicationRequestDTO application, ApplicationStatus status) {
        TripApplication temp = applicationConverter.RequestDTOToEntity(RetrieveByUserIDAndTripID(application.getUser(),application.getTrip()));
        temp.setStatus(status);
        saved.Update(temp);
        //mailer.sendApplicationNotification(application.getUser());
    }

    @Override
    public void Update(ApplicationRequestDTO application) {
        TripApplication temp = applicationConverter.RequestDTOToEntity(RetrieveByUserIDAndTripID(application.getUser(),application.getTrip()));
        if(temp.getStatus().equals(ApplicationStatus.PENDING)){
            saved.Update(applicationConverter.RequestDTOToEntity(application));
        }
    }
}
