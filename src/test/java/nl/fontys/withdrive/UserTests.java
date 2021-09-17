package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.repositories.FakeUserData;
import nl.fontys.withdrive.services.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTests {
    @Test
    public void TestCreateUser(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        //Act
        List<UserDTO> result = users.RetrieveAll();
        //Assert
        Assertions.assertFalse(result.isEmpty());
    }
    @Test
    public void TestCreatingUserAndTestDetails(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        //Act
        users.Add(new UserDTO(1, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        UserDTO result = users.RetrieveByNumber(1);
        //Assert
        Assertions.assertEquals(1,result.getClientNumber());
        Assertions.assertEquals("EmilyJones@example.com",result.getEmail());
        Assertions.assertEquals("Emily",result.getFirstName());
        Assertions.assertEquals("Jones",result.getLastName());
        Assertions.assertEquals("19-11-1998",result.getDateOfBirth());
        Assertions.assertEquals("Female",result.getGender());
        Assertions.assertEquals("+3165526368",result.getPhoneNumber());
        Assertions.assertEquals("abcdef",result.getPassword());
    }
    @Test
    public void TestRetrievalOfNonExistingUser(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        //Act
        UserDTO actual = users.RetrieveByNumber(1);
        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    public void TestUpdatingAddedUserInformation(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        users.Add(new UserDTO(1, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        users.Update(new UserDTO(1, "test", "test", "test", "test", "test", "test", "test"));
        //Act
        UserDTO actual = users.RetrieveByNumber(1);

        //Assert
        Assertions.assertEquals(1,actual.getClientNumber());
        Assertions.assertEquals("test",actual.getEmail());
        Assertions.assertEquals("test",actual.getFirstName());
        Assertions.assertEquals("test",actual.getLastName());
        Assertions.assertEquals("test",actual.getDateOfBirth());
        Assertions.assertEquals("test",actual.getGender());
        Assertions.assertEquals("test",actual.getPhoneNumber());
        Assertions.assertEquals("test",actual.getPassword());
    }

    @Test
    public void TestAddUserThenDeleteUserExpectUserWasDeleted(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        users.Add(new UserDTO(1, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        //Act
        users.Delete(1);
        UserDTO result = users.RetrieveByNumber(1);
        //Assert
        Assertions.assertNull(result);
    }
}
