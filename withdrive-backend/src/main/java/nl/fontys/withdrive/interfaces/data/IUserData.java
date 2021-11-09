package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserData {
    void Create(User user);

    List<User> RetrieveAll();

    User RetrieveByID(UUID number);

    User retrieveByEmail(String email);

    Role createRole(Role role);

    void addRoleToUser(String email, String roleName);

    void Update(User client);

    void Delete(UUID number);

    List<User> RetrieveUsersByTripID(UUID trip);

    List<User> GetDrivers();
}