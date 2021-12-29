package nl.fontys.withdrive.interfaces.service;

import nl.fontys.withdrive.dto.user.UserDTO;

public interface ISanitizeService {
    boolean checkUser(UserDTO user);
}
