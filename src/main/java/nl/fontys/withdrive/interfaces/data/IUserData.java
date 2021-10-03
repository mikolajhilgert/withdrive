package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserData {
    boolean Create(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByNumber(UUID number);

    boolean Update(UserDTO client);

    boolean Delete(UUID number);

}