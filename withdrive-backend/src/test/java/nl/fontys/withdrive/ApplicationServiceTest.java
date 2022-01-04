package nl.fontys.withdrive;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.converter.ApplicationConverter;
import nl.fontys.withdrive.converter.TripConverter;
import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.TripApplicationKEY;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.converter.IApplicationConverter;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import nl.fontys.withdrive.interfaces.service.IEmailService;
import nl.fontys.withdrive.interfaces.service.ITripService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import nl.fontys.withdrive.service.ApplicationService;
import nl.fontys.withdrive.service.EmailService;
import nl.fontys.withdrive.service.TripService;
import nl.fontys.withdrive.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//User tests using mockito
@ActiveProfiles("test")
@SpringBootTest
public class ApplicationServiceTest {
    // https://javacodehouse.com/blog/mockito-tutorial/
    @Mock
    IApplicationData db;
    @Mock
    IUserData userData;
    @Mock
    IUserService userService;
    @Mock
    ITripData tripData;
    @Mock
    IEmailService mailService;

    IApplicationService service;
    IApplicationConverter converter;

    UUID tripID = UUID.randomUUID();
    UUID driverID = UUID.randomUUID();
    UUID userID = UUID.randomUUID();
    UUID userID2 = UUID.randomUUID();

    @BeforeEach
    public void setUp()  {
        converter = new ApplicationConverter(new ModelMapper(),userData,tripData);
        service = new ApplicationService(db,converter,mailService,userService);
        User driver = new User(driverID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
        User user = new User(userID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
        User user2 = new User(userID2,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
        List<Trip> trips = List.of(
                new Trip(tripID,"Eindhoven","Venlo","Quick trip","31/12/2021","AL-125-GG",2,2.5,TripStatus.OPEN,null,null,driver)
        );
        List<TripApplication> apps = List.of(
                new TripApplication(new TripApplicationKEY(),user,trips.get(0),ApplicationStatus.ACCEPTED,""),
                new TripApplication(new TripApplicationKEY(),user2,trips.get(0),ApplicationStatus.PENDING,"")
        );

        when(db.RetrieveByTripID(tripID)).thenReturn(apps);
        when(db.RetrieveByUserID(userID)).thenReturn(List.of(apps.get(0)));
        when(db.RetrieveByUserIDAndTripID(any(UUID.class),any(UUID.class))).thenReturn(apps.get(1));

        doNothing().when(mailService).sendApplicationNotification(any(String.class));
        doNothing().when(mailService).sendApplicationResponseNotification(any(String.class));

        when(tripData.RetrieveByNumber(tripID)).thenReturn(trips.get(0));
        when(userData.RetrieveByID(driverID)).thenReturn(driver);
        when(userData.RetrieveByID(userID)).thenReturn(user);
        when(userData.RetrieveByID(userID2)).thenReturn(user2);
        when(userService.RetrieveByID(any(UUID.class))).thenReturn(new UserDTO(userID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null));
    }

    @Test
    public void applyUserToTrip()
    {
        service.Add(new ApplicationRequestDTO(tripID,userID,ApplicationStatus.PENDING,""),userID);

        ArgumentCaptor<TripApplication> UserArgumentCaptor = ArgumentCaptor.forClass(TripApplication.class);
        verify(db).Create(UserArgumentCaptor.capture());
        TripApplication finalResult = UserArgumentCaptor.getValue();
        Assertions.assertEquals(finalResult.getApplicant(), userData.RetrieveByID(userID));
        Assertions.assertEquals(finalResult.getTrip(), tripData.RetrieveByNumber(tripID));
    }
    @Test
    public void applyUserToTripAndAccept()
    {
        service.Add(new ApplicationRequestDTO(tripID,userID,ApplicationStatus.PENDING,""),userID);
        service.RespondToApplication(new ApplicationRequestDTO(tripID,userID,ApplicationStatus.PENDING,""),ApplicationStatus.ACCEPTED);

        ArgumentCaptor<TripApplication> UserArgumentCaptor = ArgumentCaptor.forClass(TripApplication.class);
        verify(db).Update(UserArgumentCaptor.capture());
        TripApplication finalResult = UserArgumentCaptor.getValue();

        Assertions.assertEquals(finalResult.getApplicant().getUserID(), userID2);
        Assertions.assertEquals(finalResult.getStatus(), ApplicationStatus.ACCEPTED);
    }
    @Test
    public void applyUserToTripAndReject()
    {
        service.Add(new ApplicationRequestDTO(tripID,userID,ApplicationStatus.PENDING,""),userID);
        service.RespondToApplication(new ApplicationRequestDTO(tripID,userID,ApplicationStatus.PENDING,""),ApplicationStatus.REJECTED);

        ArgumentCaptor<TripApplication> UserArgumentCaptor = ArgumentCaptor.forClass(TripApplication.class);
        verify(db).Update(UserArgumentCaptor.capture());
        TripApplication finalResult = UserArgumentCaptor.getValue();

        Assertions.assertEquals(finalResult.getApplicant().getUserID(), userID2);
        Assertions.assertEquals(finalResult.getStatus(), ApplicationStatus.REJECTED);
    }
    @Test
    public void getApplicationsPerTrip()
    {
        List<ApplicationResponseDTO> output = service.RetrieveByTripID(tripID);
        verify(db).RetrieveByTripID(tripID);

        Assertions.assertEquals(output.size(), 2);
        Assertions.assertEquals(output.get(0).getStatus(), ApplicationStatus.ACCEPTED);
        Assertions.assertEquals(output.get(1).getStatus(), ApplicationStatus.PENDING);
    }
    @Test
    public void getApplicationsPerUser()
    {
        List<ApplicationResponseDTO> output = service.RetrieveActiveByUserID(userID);

        verify(db).RetrieveByUserID(userID);
        Assertions.assertEquals(output.size(), 1);
        Assertions.assertEquals(output.get(0).getStatus(), ApplicationStatus.ACCEPTED);
    }
    @Test
    public void getApplicationsPerUserAndTrip()
    {
        ApplicationRequestDTO output = service.RetrieveByUserIDAndTripID(userID2,tripID);

        verify(db).RetrieveByUserIDAndTripID(userID2,tripID);
        Assertions.assertEquals(output.getStatus(), ApplicationStatus.PENDING);
    }
}
