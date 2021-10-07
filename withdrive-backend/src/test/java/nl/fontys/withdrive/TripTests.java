package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.repositories.FakeTripData;
import nl.fontys.withdrive.repositories.FakeUserData;
import nl.fontys.withdrive.services.TripManager;
import nl.fontys.withdrive.services.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

//    @Test
//    public void TestEditTripDetails(){
//
//    }
}
