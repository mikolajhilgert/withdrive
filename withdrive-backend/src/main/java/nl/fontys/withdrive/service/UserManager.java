package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserManager implements IUserManager {
    private final IUserData saved;
    private final IUserConverter converter;

    @Autowired
    public UserManager(IUserData saved, IUserConverter converter){
        this.saved = saved;
        this.converter = converter;
    }

    @Override
    public boolean Add(UserDTO user) {
        this.saved.Create(converter.DTOToEntity(user));
        return true;
    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return converter.ListEntityToDTO(this.saved.RetrieveAll());
    }

    @Override
    public UserDTO RetrieveByID(UUID id) {
        return converter.EntityToDTO(this.saved.RetrieveByID(id));
    }

    @Override
    public boolean Update(UserDTO user) {
        this.saved.Update(converter.DTOToEntity(user));
        return true;
    }

    @Override
    public boolean Delete(UUID number) {
        this.saved.Delete(number);
        return true;
    }
}
