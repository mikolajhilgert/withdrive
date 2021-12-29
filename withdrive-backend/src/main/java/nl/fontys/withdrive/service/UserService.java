package nl.fontys.withdrive.service;

import com.sun.mail.imap.Utility;
import lombok.extern.slf4j.Slf4j;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.interfaces.service.IEmailService;
import nl.fontys.withdrive.interfaces.service.ISanitizeService;
import nl.fontys.withdrive.interfaces.service.IUserService;
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

@Service @Transactional
public class UserService implements IUserService, UserDetailsService {
    private final IUserData saved;
    private final IUserConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final ISanitizeService sanitize;
    private final IEmailService mailer;

    @Autowired
    public UserService(IUserData saved, IUserConverter converter, PasswordEncoder passwordEncoder, ISanitizeService sanitize,IEmailService mailer){
        this.saved = saved;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.sanitize = sanitize;
        this.mailer = mailer;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = saved.retrieveByEmail(email);
        if(user == null){
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
        if(sanitize.checkUser(user)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //addRoleToUser(user.getEmail(),"ROLE_USER");
            this.saved.Create(converter.DTOToEntity(user));
            return true;
        }
        return false;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public boolean existsByEmail(String email) {
        return saved.existsByEmail(email);
    }

    @Override
    public void updateResetPasswordToken(String token, String email, String link) {
        User user = saved.retrieveByEmail(email);
        String resetPasswordLink =  link + "/reset-password?token=" + token;
        String content = "Hello,"
                + "You have requested to reset your password"
                + "Click the link below to change your password:"+
                resetPasswordLink;
        if (user != null) {
            mailer.sendMail(email, "You requested to reset your password!",content);
            user.setResetPasswordToken(token);
            saved.Update(user);
        }
    }

    @Override
    public UserDTO getByResetPasswordToken(String token) {
        if(saved.findByResetPasswordToken(token) != null){
            return converter.EntityToDTO(saved.findByResetPasswordToken(token));
        }
        return null;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        User user = saved.retrieveByEmail(email);
        if(user != null){
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            user.setResetPasswordToken(null);
            saved.Update(user);
        }
    }


}
