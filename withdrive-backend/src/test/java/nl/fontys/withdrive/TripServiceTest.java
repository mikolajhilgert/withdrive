package nl.fontys.withdrive;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.converter.TripConverter;
import nl.fontys.withdrive.converter.UserConverter;
import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.TripApplicationKEY;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.converter.ITripConverter;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IApplicationData;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.ITripManager;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import nl.fontys.withdrive.service.TripManager;
import nl.fontys.withdrive.service.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//User tests using mockito
@ActiveProfiles("test")
@SpringBootTest
public class TripServiceTest {
    // https://javacodehouse.com/blog/mockito-tutorial/
    @Mock
    ITripData db;
    @Mock
    IUserData userData;
    @Mock
    IApplicationData appData;

    ITripManager service;
    ITripConverter converter;
    UUID tripID = UUID.randomUUID();
    UUID tripID2 = UUID.randomUUID();
    UUID driverID = UUID.randomUUID();
    UUID userID = UUID.randomUUID();

    @BeforeEach
    public void setUp()  {
        converter = new TripConverter(new ModelMapper(),userData);
        service = new TripManager(db,userData,converter);
        User driver = new User(driverID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null);
        User user = new User(userID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null);
        List<Trip> trips = List.of(
                new Trip(tripID,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,TripStatus.AWAITING,null,driver),
                new Trip(tripID2,"Venlo","Eindhoven","Quick trip","19/11/2021","AL-125-GG",2,2.5, TripStatus.AWAITING,null,driver)
        );
        TripApplication tripApplication = new TripApplication(new TripApplicationKEY(),user,trips.get(0),ApplicationStatus.PENDING,"test");

        when(db.RetrieveByNumber(tripID)).thenReturn(trips.get(0));
        when(db.RetrieveAll()).thenReturn(trips);
        when(userData.RetrieveByID(driverID)).thenReturn(driver);
        when(userData.RetrieveByID(userID)).thenReturn(user);
    }
    @Test
    public void getAllTripsTest()
    {
        List<TripResponseDTO> output = service.RetrieveAll();

        Assertions.assertEquals(output.size(),2);
        Assertions.assertEquals(output.get(0).getTripID(), tripID);
        Assertions.assertEquals(output.get(1).getTripID(), tripID2);
    }
    @Test
    public void getTripsByIDTest()
    {
        TripResponseDTO toCheck = service.RetrieveByNumber(tripID);

        Assertions.assertEquals(toCheck.getTripID(), tripID);
    }
    @Test
    public void addTripTest(){
        TripRequestDTO trip = new TripRequestDTO(UUID.randomUUID(),"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,userData.RetrieveByID(driverID).getUserID(),null,TripStatus.AWAITING);
        service.Add(trip);

        ArgumentCaptor<Trip> UserArgumentCaptor = ArgumentCaptor.forClass(Trip.class);
        verify(db).Create(UserArgumentCaptor.capture());
        Trip finalResult = UserArgumentCaptor.getValue();
        Assertions.assertEquals(finalResult.getTripID(),trip.getTripID());
    }
    @Test
    public void editTripTest(){
        Trip trip = db.RetrieveByNumber(tripID);
        trip.setOrigin("test");
        service.Update(new TripRequestDTO(trip.getTripID(),trip.getOrigin(),trip.getDestination(),trip.getDescription(),trip.getDate(),trip.getLicensePlate(),trip.getMaxPassengers(),trip.getPricePerPassenger(),userData.RetrieveByID(driverID).getUserID(), new ArrayList<>(),trip.getStatus()));

        ArgumentCaptor<Trip> UserArgumentCaptor = ArgumentCaptor.forClass(Trip.class);
        verify(db).Update(UserArgumentCaptor.capture());
        Trip finalResult = UserArgumentCaptor.getValue();
        Assertions.assertEquals(finalResult.getTripID(),trip.getTripID());
        Assertions.assertEquals(finalResult.getOrigin(),trip.getOrigin());
    }
    @Test
    public void deleteTripByIDTest()
    {
        service.Add(new TripRequestDTO(UUID.randomUUID(),"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,userData.RetrieveByID(driverID).getUserID(),null,TripStatus.AWAITING));

        service.Delete(tripID,driverID);

        verify(db).Delete(tripID);
    }
}
