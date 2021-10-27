package nl.fontys.withdrive.interfaces.services;

import nl.fontys.withdrive.dto.user.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserManager {
    boolean Add(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByID(UUID id);

    boolean Update(UserDTO user);

    boolean Delete(UUID id);

}
