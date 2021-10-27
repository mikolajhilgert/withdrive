package nl.fontys.withdrive.interfaces.data;

import nl.fontys.withdrive.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserData {
    void Create(User user);

    List<User> RetrieveAll();

    User RetrieveByID(UUID number);

    void Update(User client);

    void Delete(UUID number);

}