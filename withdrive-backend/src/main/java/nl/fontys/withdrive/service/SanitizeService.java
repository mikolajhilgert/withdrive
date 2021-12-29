package nl.fontys.withdrive.service;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.service.ISanitizeService;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService implements ISanitizeService {
    @Override
    public boolean checkUser(UserDTO user) {
        String email = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(user.getEmail().matches(email) && user.getPassword().length() >= 6 && user.getPassword().length() <= 15){
            return true;
        }
        return false;
    }
}
