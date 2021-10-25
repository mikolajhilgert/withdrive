package nl.fontys.withdrive.repository.jpa;

import nl.fontys.withdrive.interfaces.jpa.IJPAUserData;
import nl.fontys.withdrive.interfaces.data.IUserData;
import nl.fontys.withdrive.model.dto.UserDTO;
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
    public boolean Create(UserDTO user) {
        db.save(user);
        return true;
    }

    @Override
    public List<UserDTO> RetrieveAll() {
        return db.findAll();
    }

    @Override
    public UserDTO RetrieveByNumber(UUID id) {
        return db.getFirstByClientNumber(id);
    }

    @Override
    public boolean Update(UserDTO client) {
        UserDTO toUpdate = RetrieveByNumber(client.getClientNumber());
        toUpdate.setEmail(client.getEmail());
        db.save(toUpdate);
        return true;
    }

    @Override
    public boolean Delete(UUID number) {
        db.deleteById(number);
        return true;
    }
}
