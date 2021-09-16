package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.repositories.FakeUserData;
import nl.fontys.withdrive.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTests {
    @Test
    public void TestCreateUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        List<UserDTO> expected = users.RetrieveAll();
        //Assert
        Assertions.assertNotNull(expected);
    }
}
