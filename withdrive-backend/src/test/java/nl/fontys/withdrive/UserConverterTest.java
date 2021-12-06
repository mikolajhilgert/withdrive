package nl.fontys.withdrive;

import nl.fontys.withdrive.converter.UserConverter;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;


public class UserConverterTest {
    IUserConverter converter = new UserConverter(new ModelMapper());
    UUID testID = UUID.randomUUID();
    UUID testID2 = UUID.randomUUID();

    @Test
    public void convertUserDTOToEntityTest()
    {
        UserDTO start = new UserDTO(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null);
        User wantedResult = new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null);

        User toTest = converter.DTOToEntity(start);

        Assertions.assertEquals(toTest.getUserID(),wantedResult.getUserID());
        Assertions.assertEquals(toTest.getEmail(),wantedResult.getEmail());
        Assertions.assertEquals(toTest.getDateOfBirth(),wantedResult.getDateOfBirth());
        Assertions.assertEquals(toTest.getGender(),wantedResult.getGender());
        Assertions.assertEquals(toTest.getFirstName(),wantedResult.getFirstName());
        Assertions.assertEquals(toTest.getLastName(),wantedResult.getLastName());
        Assertions.assertEquals(toTest.getPassword(),wantedResult.getPassword());
        Assertions.assertEquals(toTest.getPhoneNumber(),wantedResult.getPhoneNumber());
    }
    @Test
    public void convertUserEntityToDTOTest()
    {
        User start = new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null);
        UserDTO wantedResult = new UserDTO(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null);


        UserDTO toTest = converter.EntityToDTO(start);

        Assertions.assertEquals(toTest.getUserID(),wantedResult.getUserID());
        Assertions.assertEquals(toTest.getEmail(),wantedResult.getEmail());
        Assertions.assertEquals(toTest.getDateOfBirth(),wantedResult.getDateOfBirth());
        Assertions.assertEquals(toTest.getGender(),wantedResult.getGender());
        Assertions.assertEquals(toTest.getFirstName(),wantedResult.getFirstName());
        Assertions.assertEquals(toTest.getLastName(),wantedResult.getLastName());
        Assertions.assertEquals(toTest.getPassword(),wantedResult.getPassword());
        Assertions.assertEquals(toTest.getPhoneNumber(),wantedResult.getPhoneNumber());
    }
    @Test
    public void convertListUserEntityToDTOTest()
    {
        List<User> start = List.of(
                new User(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null,null,null,null,null),
                new User(testID2,"emily.black@gmail.com","Emily","Black","10-02-1990","Female","678988273","hello",null,null,null,null,null)
        );

        List<UserDTO> wantedResult = List.of(
                new UserDTO(testID,"john.doe@gmail.com","John","Doe","10-02-1990","Male","789762183","password",null),
                new UserDTO(testID2,"emily.black@gmail.com","Emily","Black","10-02-1990","Female","678988273","hello",null)
        );

        List<UserDTO> toTest = converter.ListEntityToDTO(start);

        Assertions.assertEquals(toTest.get(0).getUserID(),wantedResult.get(0).getUserID());
        Assertions.assertEquals(toTest.get(0).getEmail(),wantedResult.get(0).getEmail());
        Assertions.assertEquals(toTest.get(0).getDateOfBirth(),wantedResult.get(0).getDateOfBirth());
        Assertions.assertEquals(toTest.get(0).getGender(),wantedResult.get(0).getGender());
        Assertions.assertEquals(toTest.get(0).getFirstName(),wantedResult.get(0).getFirstName());
        Assertions.assertEquals(toTest.get(0).getLastName(),wantedResult.get(0).getLastName());
        Assertions.assertEquals(toTest.get(0).getPassword(),wantedResult.get(0).getPassword());
        Assertions.assertEquals(toTest.get(0).getPhoneNumber(),wantedResult.get(0).getPhoneNumber());
        Assertions.assertEquals(toTest.get(1).getUserID(),wantedResult.get(1).getUserID());
        Assertions.assertEquals(toTest.get(1).getEmail(),wantedResult.get(1).getEmail());
        Assertions.assertEquals(toTest.get(1).getDateOfBirth(),wantedResult.get(1).getDateOfBirth());
        Assertions.assertEquals(toTest.get(1).getGender(),wantedResult.get(1).getGender());
        Assertions.assertEquals(toTest.get(1).getFirstName(),wantedResult.get(1).getFirstName());
        Assertions.assertEquals(toTest.get(1).getLastName(),wantedResult.get(1).getLastName());
        Assertions.assertEquals(toTest.get(1).getPassword(),wantedResult.get(1).getPassword());
        Assertions.assertEquals(toTest.get(1).getPhoneNumber(),wantedResult.get(1).getPhoneNumber());
    }
}
