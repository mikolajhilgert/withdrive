package nl.fontys.withdrive.service;

import lombok.extern.slf4j.Slf4j;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service @Transactional @Slf4j
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
    public UserDTO retrieveByEmail(String email) {
        return null;
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

    @Override
    public Role saveRole(Role role) {
        return saved.createRole(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        saved.addRoleToUser(email,roleName);
    }
}
