package nl.fontys.withdrive.services;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.UserStorage;
import nl.fontys.withdrive.interfaces.UserSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserSupplier {
    private final UserStorage saved;

    @Autowired
    UserManager(UserStorage saved){
        this.saved = saved;
    }

    @Override
    public boolean Add(UserDTO user) {
        return this.saved.Create(user);
    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return this.saved.RetrieveAll();
    }

    @Override
    public UserDTO RetrieveByNumber(int number) {
        return this.saved.RetrieveByNumber(number);
    }

    @Override
    public boolean Update(UserDTO user) {
        return this.saved.Update(user);
    }

    @Override
    public boolean Delete(int number) {
        return this.saved.Delete(number);
    }
}
