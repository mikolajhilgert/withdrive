package nl.fontys.withdrive;

import nl.fontys.withdrive.converter.UserConverter;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.service.ISanitizeService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import nl.fontys.withdrive.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

//User tests using mockito
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {
    // https://javacodehouse.com/blog/mockito-tutorial/
    @Mock
    IUserData db;
    @Mock
    IUserService service;
    @Mock
    ISanitizeService sanitize;
    IUserConverter converter = new UserConverter(new ModelMapper());
    UUID testID = UUID.randomUUID();
    UUID testID2 = UUID.randomUUID();

    @BeforeEach
    public void setUp()  {
        service = new UserService(db,converter, new BCryptPasswordEncoder(), sanitize, null);
        when(sanitize.checkUser(any(UserDTO.class))).thenReturn(true);
    }

    @Test
    public void getAllUsersTest()
    {
        List<User> users = List.of(
                new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null),
                new User(testID2,"emily.black@gmail.com","Emily","Black","10-02-1990","Female","678988273","hello",null,null,null,null,null,null)
        );
        when(db.RetrieveAll()).thenReturn(users);

        List<UserDTO> output = service.RetrieveAll();

        Assertions.assertEquals(output.size(),2);
        Assertions.assertEquals(output.get(0).getUserID(), testID);
        Assertions.assertEquals(output.get(1).getUserID(), testID2);
    }
    @Test
    public void getUserByIDTest()
    {
        User user = new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
        when(db.RetrieveByID(testID)).thenReturn(user);

        UserDTO toCheck = service.RetrieveByID(testID);

        Assertions.assertEquals(toCheck.getUserID(), testID);
    }
    @Test
    public void addUserTest()
    {
        UserDTO user = new UserDTO(UUID.randomUUID(),"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null);
        service.Add(user);

        ArgumentCaptor<User> UserArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(db).Create(UserArgumentCaptor.capture());
        User finalResult = UserArgumentCaptor.getValue();
        Assertions.assertEquals(finalResult.getUserID(),user.getUserID());
    }
    @Test
    public void editUserTest()
    {
        UserDTO user = new UserDTO(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null);
        service.Add(user);
        user.setEmail("test");
        service.Update(user);
        when(db.RetrieveByID(testID)).thenReturn(converter.DTOToEntity(user));

        ArgumentCaptor<User> UserArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(db).Update(UserArgumentCaptor.capture());
        User finalResult = UserArgumentCaptor.getValue();
        Assertions.assertEquals(finalResult.getUserID(),user.getUserID());
        Assertions.assertEquals(finalResult.getEmail(),user.getEmail());
    }
    @Test
    public void deleteUserByIDTest()
    {
        User user = new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null,null);
        when(db.RetrieveByID(testID)).thenReturn(user);
        service.Delete(testID);

        verify(db).Delete(testID);
    }
}
