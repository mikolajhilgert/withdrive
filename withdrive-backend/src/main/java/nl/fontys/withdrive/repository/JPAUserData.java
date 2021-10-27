package nl.fontys.withdrive.repository;

import nl.fontys.withdrive.interfaces.jpa.IJPAUserData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JPAUserData implements IUserData {
    private final IJPAUserData db;
    @Autowired
    public JPAUserData(IJPAUserData db){
        this.db = db;
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
    public void Update(User client) {
        User toUpdate = RetrieveByID(client.getUserID());
        toUpdate.setEmail(client.getEmail());
        toUpdate.setDateOfBirth(client.getDateOfBirth());
        toUpdate.setFirstName(client.getFirstName());
        toUpdate.setLastName(client.getLastName());
        toUpdate.setGender(client.getGender());
        toUpdate.setPhoneNumber(client.getPhoneNumber());
        toUpdate.setPassword(client.getPassword());
        db.save(toUpdate);
    }

    @Override
    public void Delete(UUID number) {
        db.deleteById(number);
    }
}
