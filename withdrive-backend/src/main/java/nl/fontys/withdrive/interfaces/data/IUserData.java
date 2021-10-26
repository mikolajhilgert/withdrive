package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserData {
    void Create(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByNumber(UUID number);

    void Update(UserDTO client);

    void Delete(UUID number);

}