package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserManager {
    boolean Add(UserDTO user);

    List<UserDTO> RetrieveAll();

    UserDTO RetrieveByID(UUID id);

    UserDTO retrieveByEmail(String email);

    boolean Update(UserDTO user);

    boolean Delete(UUID id);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);
}
