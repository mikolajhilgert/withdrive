package nl.fontys.withdrive.service;

import nl.fontys.withdrive.model.dto.UserDTO;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserManager implements IUserManager {
    private final IUserData saved;

    @Autowired
    public UserManager(IUserData saved){
        this.saved = saved;
    }

    @Override
    public boolean Add(UserDTO user) { return this.saved.Create(user);    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return this.saved.RetrieveAll();
    }

    @Override
    public UserDTO RetrieveByNumber(UUID number) {
        return this.saved.RetrieveByNumber(number);
    }

    @Override
    public boolean Update(UserDTO user) {
        return this.saved.Update(user);
    }

    @Override
    public boolean Delete(UUID number) {
        return this.saved.Delete(number);
    }
}
