package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJPAUserData extends JpaRepository<User, UUID> {
    User getFirstByUserID(UUID ID);
}
