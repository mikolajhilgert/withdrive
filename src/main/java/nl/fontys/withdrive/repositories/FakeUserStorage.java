package nl.fontys.withdrive.repositories;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.UserStorage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeUserStorage implements UserStorage {
    private final List<UserDTO> users;

    public FakeUserStorage(){
        users = new ArrayList<>();
        users.add(new UserDTO(0,"JohnDoe@example.com","John","Doe","10-05-2002","Male","+42060605797","qwerty"));
    }

    @Override
    public boolean Create(UserDTO user) {
        UserDTO temp = RetrieveClientByNumber(user.getClientNumber());

        if(temp == null){
            this.users.add(user);
            return true;
        }
        return  false;
    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return this.users;
    }

    @Override
    public UserDTO RetrieveByNumber(int number) {
        return RetrieveClientByNumber(number);
    }

    @Override
    public boolean Update(UserDTO user) {
        UserDTO temp = RetrieveClientByNumber(user.getClientNumber());

        if(temp != null){
            //Potentially could delete old and create new object but ensure the id will be the same?
            temp.setEmail(user.getEmail());
            temp.setFirstName(user.getFirstName());
            temp.setLastName(user.getLastName());
            temp.setDateOfBirth(user.getDateOfBirth());
            temp.setGender(user.getGender());
            temp.setPhoneNumber(user.getPhoneNumber());
            temp.setPassword(user.getPassword());
            return true;
        }
        return  false;
    }

    @Override
    public boolean Delete(int number) {
        UserDTO temp = RetrieveClientByNumber(number);

        if(temp != null){
            this.users.remove(temp);
            return true;
        }
        return false;
    }

    private UserDTO RetrieveClientByNumber(int number){
        for (UserDTO user : users) {
            if (user.getClientNumber() == number)
                return user;
        }
        return  null;
    }
}


