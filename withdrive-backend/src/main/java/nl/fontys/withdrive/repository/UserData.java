package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.interfaces.jpa.IJPARoleData;
import nl.fontys.withdrive.interfaces.jpa.IJPAUserData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository @Transactional
public class UserData implements IUserData {
    private final IJPAUserData db;
    private final IJPARoleData roles;
    @Autowired
    public UserData(IJPAUserData db, IJPARoleData roles){
        this.db = db;
        this.roles = roles;
    }

    @Override
    public void Create(User user) {
        Role role = this.roles.findByName("ROLE_USER");
        Collection<Role> temp = new ArrayList<>();
        temp.add(role);
        user.setRoles(temp);
        db.save(user);
    }

    @Override
    public List<User> RetrieveAll() {
        return db.findAll();
    }

    @Override
    public User RetrieveByID(UUID id) {
        return db.getFirstByUserID(id);
    }

    @Override
    public User retrieveByEmail(String email) {
        return db.getUserByEmail(email);
    }

    @Override
    public Role createRole(Role role) {
        return roles.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = db.getUserByEmail(email);
        Role role = roles.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void Update(User user) {

        User toUpdate = RetrieveByID(user.getUserID());
        if(user.getPassword() != null){
            toUpdate.setPassword(user.getPassword());
        }
        if(toUpdate != null){
            toUpdate.setUserID(user.getUserID());
            toUpdate.setEmail(user.getEmail());
            toUpdate.setDateOfBirth(user.getDateOfBirth());
            toUpdate.setFirstName(user.getFirstName());
            toUpdate.setLastName(user.getLastName());
            toUpdate.setGender(user.getGender());
            toUpdate.setPhoneNumber(user.getPhoneNumber());

            db.save(toUpdate);
        }

    }

    @Override
    public void Delete(UUID number) {
        db.deleteById(number);
    }

    @Override
    public List<User> RetrieveUsersByTripID(UUID trip) {
        return db.getPassengerUserByTripID(trip.toString());
    }

    @Override
    public List<User> GetDrivers() {
        return db.getDrivers();
    }

    @Override
    public boolean existsByEmail(String email) {
        if(db.countUsersByEmail(email) == 0){
            return false;
        }
        return  true;
    }

    @Override
    public User findByResetPasswordToken(String token) {
        return db.getByResetPasswordToken(token);
    }
}
