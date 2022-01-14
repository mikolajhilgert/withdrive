package nl.fontys.withdrive;

import nl.fontys.withdrive.converter.TripConverter;
import nl.fontys.withdrive.converter.UserConverter;
import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.dto.user.UserMiniDTO;
import nl.fontys.withdrive.entity.Trip;
import nl.fontys.withdrive.entity.TripApplication;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.ITripData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.repository.UserData;
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
public class TripConverterTest {
    @Mock
    IUserData userData;


    UUID testID = UUID.randomUUID();
    UUID testID2 = UUID.randomUUID();
    UUID driverID = UUID.randomUUID();
    TripConverter converter;
    User driver = new User(driverID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
    UserMiniDTO driverMini = new UserMiniDTO(driverID);


    @BeforeEach
    public void setUp()  {
        converter = new TripConverter(new ModelMapper(), userData);
        when(userData.RetrieveByID(any(UUID.class))).thenReturn(driver);
    }

    @Test
    public void convertRequestDTOToEntityTest()
    {
        TripRequestDTO start = new TripRequestDTO(testID,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,driverID,null, TripStatus.OPEN);

        Trip wantedResult = new Trip(testID,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,TripStatus.OPEN,null,null,this.driver);

        Trip toTest = converter.RequestDTOToEntity(start);

        Assertions.assertEquals(toTest.getTripID(),wantedResult.getTripID());
        Assertions.assertEquals(toTest.getOrigin(),wantedResult.getOrigin());
        Assertions.assertEquals(toTest.getDate(),wantedResult.getDate());
        Assertions.assertEquals(toTest.getDescription(),wantedResult.getDescription());
        Assertions.assertEquals(toTest.getDestination(),wantedResult.getDestination());
        Assertions.assertEquals(toTest.getDriver(),wantedResult.getDriver());
        Assertions.assertEquals(toTest.getStatus(),wantedResult.getStatus());
        Assertions.assertEquals(toTest.getPricePerPassenger(),wantedResult.getPricePerPassenger());
        Assertions.assertEquals(toTest.getMaxPassengers(),wantedResult.getMaxPassengers());
        Assertions.assertEquals(toTest.getLicensePlate(),wantedResult.getLicensePlate());
    }
    @Test
    public void convertUserEntityToDTOTest()
    {
        Trip start = new Trip(testID,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,TripStatus.OPEN,null,null,this.driver);
        TripResponseDTO wantedResult = new TripResponseDTO(testID,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,driverMini, TripStatus.OPEN, Collections.emptyList());

        TripResponseDTO toTest = converter.EntityToResponseDTO(start);

        Assertions.assertEquals(toTest.getTripID(),wantedResult.getTripID());
        Assertions.assertEquals(toTest.getOrigin(),wantedResult.getOrigin());
        Assertions.assertEquals(toTest.getDate(),wantedResult.getDate());
        Assertions.assertEquals(toTest.getDescription(),wantedResult.getDescription());
        Assertions.assertEquals(toTest.getDestination(),wantedResult.getDestination());
        Assertions.assertEquals(toTest.getDriver().getUserID(),wantedResult.getDriver().getUserID());
        Assertions.assertEquals(toTest.getStatus(),wantedResult.getStatus());
        Assertions.assertEquals(toTest.getPricePerPassenger(),wantedResult.getPricePerPassenger());
        Assertions.assertEquals(toTest.getMaxPassengers(),wantedResult.getMaxPassengers());
        Assertions.assertEquals(toTest.getLicensePlate(),wantedResult.getLicensePlate());
    }
    @Test
    public void convertListTripEntityToDTOTest()
    {
        List<Trip> start = List.of(
                new Trip(testID,"Venlo","Eindhoven","Quick trip","19/11/2021","AL-125-GG",2,2.5,TripStatus.OPEN,null,null,this.driver),
                new Trip(testID2,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,TripStatus.OPEN,null,null,this.driver)
        );

        List<TripResponseDTO> wantedResult = List.of(
                new TripResponseDTO(testID,"Venlo","Eindhoven","Quick trip","19/11/2021","AL-125-GG",2,2.5,driverMini, TripStatus.OPEN, Collections.emptyList()),
                new TripResponseDTO(testID2,"Eindhoven","Venlo","Quick trip","19/11/2021","AL-125-GG",2,2.5,driverMini, TripStatus.OPEN, Collections.emptyList())
        );

        List<TripResponseDTO> toTest = converter.ListEntityToResponseDTO(start);

        Assertions.assertEquals(toTest.get(0).getTripID(),wantedResult.get(0).getTripID());
        Assertions.assertEquals(toTest.get(0).getOrigin(),wantedResult.get(0).getOrigin());
        Assertions.assertEquals(toTest.get(0).getDate(),wantedResult.get(0).getDate());
        Assertions.assertEquals(toTest.get(0).getDescription(),wantedResult.get(0).getDescription());
        Assertions.assertEquals(toTest.get(0).getDestination(),wantedResult.get(0).getDestination());
        Assertions.assertEquals(toTest.get(0).getDriver().getUserID(),wantedResult.get(0).getDriver().getUserID());
        Assertions.assertEquals(toTest.get(0).getStatus(),wantedResult.get(0).getStatus());
        Assertions.assertEquals(toTest.get(0).getPricePerPassenger(),wantedResult.get(0).getPricePerPassenger());
        Assertions.assertEquals(toTest.get(0).getMaxPassengers(),wantedResult.get(0).getMaxPassengers());
        Assertions.assertEquals(toTest.get(0).getLicensePlate(),wantedResult.get(0).getLicensePlate());

        Assertions.assertEquals(toTest.get(1).getTripID(),wantedResult.get(1).getTripID());
        Assertions.assertEquals(toTest.get(1).getOrigin(),wantedResult.get(1).getOrigin());
        Assertions.assertEquals(toTest.get(1).getDate(),wantedResult.get(1).getDate());
        Assertions.assertEquals(toTest.get(1).getDescription(),wantedResult.get(1).getDescription());
        Assertions.assertEquals(toTest.get(1).getDestination(),wantedResult.get(1).getDestination());
        Assertions.assertEquals(toTest.get(1).getDriver().getUserID(),wantedResult.get(1).getDriver().getUserID());
        Assertions.assertEquals(toTest.get(1).getStatus(),wantedResult.get(1).getStatus());
        Assertions.assertEquals(toTest.get(1).getPricePerPassenger(),wantedResult.get(1).getPricePerPassenger());
        Assertions.assertEquals(toTest.get(1).getMaxPassengers(),wantedResult.get(1).getMaxPassengers());
        Assertions.assertEquals(toTest.get(1).getLicensePlate(),wantedResult.get(1).getLicensePlate());
    }
}
