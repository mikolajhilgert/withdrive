package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.interfaces.jpa.IJPARoleData;
import nl.fontys.withdrive.interfaces.jpa.IJPAUserData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class JPAUserData implements IUserData {
    private final IJPAUserData db;
    private final IJPARoleData roles;
    @Autowired
    public JPAUserData(IJPAUserData db, IJPARoleData roles){
        this.db = db;
        this.roles = roles;
    }

    @Override
    public void Create(User user) {
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
        toUpdate.setEmail(user.getEmail());
        toUpdate.setDateOfBirth(user.getDateOfBirth());
        toUpdate.setFirstName(user.getFirstName());
        toUpdate.setLastName(user.getLastName());
        toUpdate.setGender(user.getGender());
        toUpdate.setPhoneNumber(user.getPhoneNumber());
        toUpdate.setPassword(user.getPassword());
        db.save(toUpdate);
    }

    @Override
    public void Delete(UUID number) {
        db.deleteById(number);
    }

    @Override
    public List<User> RetrieveUsersByTripID(UUID trip) {
        return db.getPassangerUserByTripID(trip.toString());
    }

    @Override
    public List<User> GetDrivers() {
        return db.getDrivers();
    }
}
