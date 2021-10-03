package nl.fontys.withdrive.repositories;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.data.IUserData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserData implements IUserData {
    private final List<UserDTO> users;

    public FakeUserData() {
        users = new ArrayList<>();
        users.add(new UserDTO(UUID.randomUUID(), "JohnDoe@example.com", "John", "Doe", "10-05-2002", "Male", "+42060605797", "qwerty"));
    }

    @Override
    public boolean Create(UserDTO user) {
        UserDTO temp = RetrieveClientByNumber(user.getClientNumber());

        if (temp == null) {
            this.users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return this.users;
    }

    @Override
    public UserDTO RetrieveByNumber(UUID number) {
        return RetrieveClientByNumber(number);
    }

    @Override
    public boolean Update(UserDTO user) {
        UserDTO temp = RetrieveClientByNumber(user.getClientNumber());

        if (temp != null) {
            return this.users.set(this.users.indexOf(temp),user) != null;
        }
        return false;
    }

    @Override
    public boolean Delete(UUID number) {
        UserDTO temp = RetrieveClientByNumber(number);

        if (temp != null) {
            this.users.remove(temp);
            return true;
        }
        return false;
    }

    private UserDTO RetrieveClientByNumber(UUID number) {
        for (UserDTO user : users) {
            if (user.getClientNumber().equals(number))
                return user;
        }
        return null;
    }
}