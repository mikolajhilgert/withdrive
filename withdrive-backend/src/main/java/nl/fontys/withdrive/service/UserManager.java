package nl.fontys.withdrive.service;

import lombok.extern.slf4j.Slf4j;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service @Transactional @Slf4j
public class UserManager implements IUserManager, UserDetailsService {
    private final IUserData saved;
    private final IUserConverter converter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(IUserData saved, IUserConverter converter, PasswordEncoder passwordEncoder){
        this.saved = saved;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = saved.retrieveByEmail(email);
        if(user == null){
            log.error("User was not found in the database");
            throw new UsernameNotFoundException("User was not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    @Override
    public boolean Add(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //addRoleToUser(user.getEmail(),"ROLE_USER");
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
        return converter.EntityToDTO(this.saved.retrieveByEmail(email));
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
