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
        List<UserDTO> result = users.RetrieveAll();
        //Assert
        Assertions.assertFalse(result.isEmpty());
    }
    @Test
    public void TestUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        UserDTO actual = users.RetrieveAll().get(0);
        //Assert
        Assertions.assertEquals(0,actual.getClientNumber());
        Assertions.assertEquals("JohnDoe@example.com",actual.getEmail());
        Assertions.assertEquals("John",actual.getFirstName());
        Assertions.assertEquals("Doe",actual.getLastName());
        Assertions.assertEquals("10-05-2002",actual.getDateOfBirth());
        Assertions.assertEquals("Male",actual.getGender());
        Assertions.assertEquals("+42060605797",actual.getPhoneNumber());
        Assertions.assertEquals("qwerty",actual.getPassword());
    }
    @Test
    public void TestNonExistingUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        UserDTO actual = users.RetrieveByNumber(1);
        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    public void TestCreatingUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        users.Add(new UserDTO(1, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        UserDTO result = users.RetrieveByNumber(1);
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void TestUpdatingUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        users.Update(new UserDTO(0, "test", "test", "test", "test", "test", "test", "test"));

        UserDTO actual = users.RetrieveByNumber(0);
        //Assert
        Assertions.assertEquals(0,actual.getClientNumber());
        Assertions.assertEquals("test",actual.getEmail());
        Assertions.assertEquals("test",actual.getFirstName());
        Assertions.assertEquals("test",actual.getLastName());
        Assertions.assertEquals("test",actual.getDateOfBirth());
        Assertions.assertEquals("test",actual.getGender());
        Assertions.assertEquals("test",actual.getPhoneNumber());
        Assertions.assertEquals("test",actual.getPassword());
    }

    @Test
    public void TestDeleteUser(){
        //Arrange
        UserService users = new UserService(new FakeUserData());
        //Act
        users.Delete(0);
        List<UserDTO> result = users.RetrieveAll();
        //Assert
        Assertions.assertTrue(result.isEmpty());
    }
}
