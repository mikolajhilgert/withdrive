package nl.fontys.withdrive.interfaces.services;

import nl.fontys.withdrive.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserManager {
    boolean Add(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByNumber(UUID number);

    boolean Update(UserDTO client);

    boolean Delete(UUID number);

}
