package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.repositories.FakeUserData;
import nl.fontys.withdrive.services.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class UserTests {
    //5 Tests as expected of first sprint
    @Test
    public void TestIfRetrieveAllUsersWorks(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        users.Add(new UserDTO(UUID.randomUUID(), "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        //Act
        List<UserDTO> result = users.RetrieveAll();
        //Assert
        Assertions.assertFalse(result.isEmpty());
    }
    @Test
    public void TestCreatingUserAndTestDetails(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        UUID temp = UUID.randomUUID();
        //Act
        users.Add(new UserDTO(temp, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        UserDTO result = users.RetrieveByNumber(temp);
        //Assert
        Assertions.assertEquals(temp,result.getClientNumber());
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
        UserDTO actual = users.RetrieveByNumber(UUID.randomUUID());
        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    public void TestUpdatingAddedUserInformation(){
        //Arrange
        UserManager users = new UserManager(new FakeUserData());
        UUID temp = UUID.randomUUID();
        users.Add(new UserDTO(temp, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        users.Update(new UserDTO(temp, "test", "test", "test", "test", "test", "test", "test"));
        //Act
        UserDTO actual = users.RetrieveByNumber(temp);

        //Assert
        Assertions.assertEquals(temp,actual.getClientNumber());
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
        UUID temp = UUID.randomUUID();
        users.Add(new UserDTO(temp, "EmilyJones@example.com", "Emily", "Jones", "19-11-1998", "Female", "+3165526368", "abcdef"));
        //Act
        users.Delete(temp);
        UserDTO result = users.RetrieveByNumber(temp);
        //Assert
        Assertions.assertNull(result);
    }
}
