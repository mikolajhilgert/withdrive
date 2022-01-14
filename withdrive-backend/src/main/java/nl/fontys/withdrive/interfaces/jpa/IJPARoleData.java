package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IJPARoleData extends JpaRepository<Role, UUID> {
    Role findByName(String name);

//    @Modifying
//    @Query("delete from users_roles where user_id = ?1")
//    void deleteRolesFromUser(String id);
}
