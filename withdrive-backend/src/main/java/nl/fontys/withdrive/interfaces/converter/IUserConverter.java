package nl.fontys.withdrive.interfaces.converter;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.User;

import java.util.List;

public interface IUserConverter {
    User DTOToEntity(UserDTO user);
    UserDTO EntityToDTO(User user);
    List<UserDTO> ListEntityToDTO(List<User> users);
}
