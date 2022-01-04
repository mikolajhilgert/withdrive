package nl.fontys.withdrive.interfaces.jpa;

import nl.fontys.withdrive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IJPAUserData extends JpaRepository<User, UUID> {
    User getFirstByUserID(UUID ID);

    User getUserByEmail(String email);

    @Query(value = "select count(*) from users where email = ?1",nativeQuery = true)
    int countUsersByEmail(String email);

    @Query(value = "SELECT users.* FROM users LEFT JOIN applications on users.id = applications.userid WHERE applications.tripID = ?1 AND applications.status = 'ACCEPTED'",nativeQuery = true)
    List<User> getPassengerUserByTripID(String id);

    @Query(value = "select distinct users.* from users right outer join trips on trips.driver_id = users.id",nativeQuery = true)
    List<User> getDrivers();

    @Query(value = "select users.* from users where reset_password_token=:token",nativeQuery = true)
    User getByResetPasswordToken(@Param("token") String token);
}
