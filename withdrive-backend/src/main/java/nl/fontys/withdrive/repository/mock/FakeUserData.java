package nl.fontys.withdrive.repository.mock;

import nl.fontys.withdrive.model.dto.UserDTO;
import nl.fontys.withdrive.interfaces.data.IUserData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Repository
public class FakeUserData implements IUserData {
    private final List<UserDTO> users;

    public FakeUserData() {
        users = new ArrayList<>();
        users.add(new UserDTO(UUID.fromString("2a1f54d3-6ed4-40d4-81b5-639c2ce8bfea"), "JohnDoe@example.com", "John", "Doe", "10-05-2002", "Male", "+42060605797", "qwerty"));
        users.add(new UserDTO(UUID.fromString("e9a346f9-5249-4811-9e0c-4f5028dd4c1f"), "MaryPoppins@example.com", "Mary", "Poppins", "10-05-1999", "Female", "+41526369568", "abcdef"));
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