package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJPAUserData extends JpaRepository<UserDTO, UUID> {
    UserDTO getFirstByClientNumber(UUID ID);
}
