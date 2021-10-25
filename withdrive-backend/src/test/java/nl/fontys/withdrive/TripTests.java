package nl.fontys.withdrive;

import nl.fontys.withdrive.model.dto.TripDTO;
import nl.fontys.withdrive.model.viewmodel.TripVM;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.repository.mock.FakeTripData;
import nl.fontys.withdrive.repository.mock.FakeUserData;
import nl.fontys.withdrive.service.TripManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TripTests {
    @Test
    public void TestIfRetrieveAllTripsReturnsTrips(){
        //Arrange
        IUserData data = new FakeUserData();
        UUID driver = data.RetrieveAll().get(0).getClientNumber();
        UUID temp = UUID.randomUUID();

        TripManager trips = new TripManager(new FakeTripData(),data);
        trips.Add(new TripVM(temp,"start","end","test",driver,Collections.emptyList()));
        //Act
        List<TripDTO> result = trips.RetrieveAll();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void TestCreateTripThenExpectCorrectDetails(){
        //Arrange
        IUserData data = new FakeUserData();
        TripManager trips = new TripManager(new FakeTripData(),data);
        UUID driver = data.RetrieveAll().get(0).getClientNumber();
        UUID temp = UUID.randomUUID();

        //Act
        trips.Add(new TripVM(temp, "og","og","og",driver,Collections.emptyList()));
        TripDTO result = trips.RetrieveByNumber(temp);


        //Assert
        Assertions.assertEquals(temp,result.getTripID());
        Assertions.assertEquals("og",result.getOrigin());
        Assertions.assertEquals("og",result.getDestination());
        Assertions.assertEquals("og",result.getDescription());
        Assertions.assertEquals(data.RetrieveByNumber(driver),result.getDriver());
        Assertions.assertTrue(result.getPassengers().isEmpty());
    }

    @Test
    public void TestEditTripThenExpectEditedDetails(){
        //Arrange
        IUserData data = new FakeUserData();
        TripManager trips = new TripManager(new FakeTripData(),data);
        UUID driver = data.RetrieveAll().get(0).getClientNumber();
        UUID temp = UUID.randomUUID();

        //Act
        trips.Add(new TripVM(temp, "og","og","og",driver,Collections.emptyList()));
        trips.Update(new TripVM(temp, "test","test","test",driver,Collections.emptyList()));
        TripDTO result = trips.RetrieveByNumber(temp);


        //Assert
        Assertions.assertEquals(temp,result.getTripID());
        Assertions.assertEquals("test",result.getOrigin());
        Assertions.assertEquals("test",result.getDestination());
        Assertions.assertEquals("test",result.getDescription());
        Assertions.assertEquals(data.RetrieveByNumber(driver),result.getDriver());
        Assertions.assertTrue(result.getPassengers().isEmpty());
    }

    @Test
    public void TestAddTripThenDeleteTripExpectUserWasDeleted(){
        //Arrange
        IUserData data = new FakeUserData();
        TripManager trips = new TripManager(new FakeTripData(),data);
        UUID driver = data.RetrieveAll().get(0).getClientNumber();
        UUID temp = UUID.randomUUID();
        
        //Act
        trips.Add(new TripVM(temp, "test","temp","temp",driver,Collections.emptyList()));
        trips.Delete(temp);
        TripDTO result = trips.RetrieveByNumber(temp);
        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void TestRetrievalOfNonExistingTrip(){
        //Arrange
        TripManager trips = new TripManager(new FakeTripData(),new FakeUserData());
        //Act
        TripDTO actual = trips.RetrieveByNumber(UUID.randomUUID());
        //Assert
        Assertions.assertNull(actual);
    }

}
