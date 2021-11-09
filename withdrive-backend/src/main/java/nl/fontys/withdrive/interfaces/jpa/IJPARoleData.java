package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJPARoleData extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
