package nl.fontys.withdrive;

import lombok.Getter;
import lombok.Setter;
import nl.fontys.withdrive.converter.ApplicationConverter;
import nl.fontys.withdrive.converter.TripConverter;
import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserMiniDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.TripApplicationKEY;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class ApplicationConverterTest {
    @Mock
    IUserData userData;
    @Mock
    ITripData tripData;


    UUID testID = UUID.randomUUID();
    UUID testID2 = UUID.randomUUID();
    UUID driverID = UUID.randomUUID();
    UUID userID = UUID.randomUUID();
    UUID userID2 = UUID.randomUUID();


    ApplicationConverter converter;
    User driver = new User(driverID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
    User user = new User(userID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
    User user2 = new User(userID2,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
    Trip trip = new Trip(testID,"Venlo","Eindhoven","Quick trip","19/11/2021","AL-125-GG",2,2.5, TripStatus.OPEN,null,null,driver);


    @BeforeEach
    public void setUp()  {
        converter = new ApplicationConverter(new ModelMapper(), userData, tripData);
        when(userData.RetrieveByID(driverID)).thenReturn(driver);
        when(userData.RetrieveByID(userID)).thenReturn(user);
        when(userData.RetrieveAll()).thenReturn(List.of(user,user2));
        when(tripData.RetrieveByNumber(any(UUID.class))).thenReturn(trip);
    }

    @Test
    public void convertRequestDTOToEntityTest() {
        ApplicationRequestDTO start = new ApplicationRequestDTO(testID, userID,ApplicationStatus.PENDING,"");

        TripApplication wantedResult = new TripApplication(new TripApplicationKEY(),user,trip,ApplicationStatus.PENDING,"");

        TripApplication toTest = converter.RequestDTOToEntity(start);

        Assertions.assertEquals(toTest.getTrip().getTripID(), wantedResult.getTrip().getTripID());
        Assertions.assertEquals(toTest.getApplicant().getUserID(), wantedResult.getApplicant().getUserID());
        Assertions.assertEquals(toTest.getStatus(), wantedResult.getStatus());
        Assertions.assertEquals(toTest.getText(), wantedResult.getText());
    }

    @Test
    public void convertEntityToRequestDTOTest() {
        TripApplication start = new TripApplication(new TripApplicationKEY(),user,trip,ApplicationStatus.PENDING,"");
        ApplicationRequestDTO wantedResult = new ApplicationRequestDTO(testID, userID,ApplicationStatus.PENDING,"");

        ApplicationRequestDTO toTest = converter.EntityToRequestDTO(start);

        Assertions.assertEquals(toTest.getTrip(), wantedResult.getTrip());
        Assertions.assertEquals(toTest.getUser(), wantedResult.getUser());
        Assertions.assertEquals(toTest.getStatus(), wantedResult.getStatus());
        Assertions.assertEquals(toTest.getText(), wantedResult.getText());
    }

    @Test
    public void convertListEntityToResponseDTOTest() {
         List<TripApplication> start = List.of(
                new TripApplication(new TripApplicationKEY(),user,trip,ApplicationStatus.PENDING,""),
                new TripApplication(new TripApplicationKEY(),user2,trip,ApplicationStatus.PENDING,"")
        );

        List<ApplicationResponseDTO> toTest = converter.ListEntityToResponseDTO(start);

        Assertions.assertEquals(toTest.get(0).getTrip().getTripID(), trip.getTripID());
        Assertions.assertEquals(toTest.get(0).getUser().getUserID(), user.getUserID());
        Assertions.assertEquals(toTest.get(0).getStatus(), ApplicationStatus.PENDING);
        Assertions.assertEquals(toTest.get(0).getText(), "");

        Assertions.assertEquals(toTest.get(1).getTrip().getTripID(), trip.getTripID());
        Assertions.assertEquals(toTest.get(1).getUser().getUserID(), user2.getUserID());
        Assertions.assertEquals(toTest.get(1).getStatus(), ApplicationStatus.PENDING);
        Assertions.assertEquals(toTest.get(1).getText(), "");
    }
}
