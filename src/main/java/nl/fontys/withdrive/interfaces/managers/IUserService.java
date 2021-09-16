package nl.fontys.withdrive.interfaces.managers;

import nl.fontys.withdrive.dto.UserDTO;

import java.util.List;

public interface IUserService {
    boolean Add(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByNumber(int number);

    boolean Update(UserDTO client);

    boolean Delete(int number);

}
