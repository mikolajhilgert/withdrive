package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.dto.UserDTO;

import java.util.List;

public interface IUserData {
    boolean Create(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByNumber(int number);

    boolean Update(UserDTO client);

    boolean Delete(int number);

}