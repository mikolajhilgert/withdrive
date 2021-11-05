package nl.fontys.withdrive.converter;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.converter.IUserConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter implements IUserConverter {

    private final ModelMapper mapper;

    public UserConverter(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public User DTOToEntity(UserDTO user) {
        return mapper.map(user,User.class);
    }

    @Override
    public UserDTO EntityToDTO(User user) {
        return mapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> ListEntityToDTO(List<User> users) {
        List<UserDTO> output = new ArrayList<>();
        for(User user : users){
            output.add(mapper.map(user,UserDTO.class));
        }
        return output;
    }


}
