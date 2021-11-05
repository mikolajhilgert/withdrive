package nl.fontys.withdrive;

import nl.fontys.withdrive.converter.TripConverter;
import nl.fontys.withdrive.converter.UserConverter;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
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
    IUserData userData;
    IApplicationData appData;
    ITripManager service;
    ITripConverter converter = new TripConverter(new ModelMapper(),userData,appData);
    UUID testID = UUID.randomUUID();
    UUID testID2 = UUID.randomUUID();
    UUID testID3 = UUID.randomUUID();

    @BeforeEach
    public void setUp()  {
        service = new TripManager(db,userData,converter);
    }

    //Work in progress
//    @Test
//    public void getAllTripsTest()
//    {
//        User driver = new User(testID2,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null);
//        User user = new User(testID3,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null);
//        Trip trip = new Trip(testID,"Eindhoven","Venlo","lol", TripStatus.AWAITING,null,driver);
//
//        when(db.RetrieveByNumber(testID)).thenReturn(trip);
//
//        TripResponseDTO toCheck = service.RetrieveByNumber(testID);
//
//        System.out.println(testID);
//        System.out.println(toCheck.getTripID());
//
//        Assertions.assertEquals(toCheck.getTripID(), testID);
//    }
}
