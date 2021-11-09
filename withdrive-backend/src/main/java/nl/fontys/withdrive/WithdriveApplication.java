package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.service.UserManager;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SpringBootApplication
public class WithdriveApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(WithdriveApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserManager userService){
        return args -> {
          userService.saveRole(new Role(UUID.fromString("81b83fb2-0727-4404-8cf2-1f73feeb2980"),"USER_DEFAULT"));
//          userService.saveRole(new Role(UUID.randomUUID(),"ROLE_USER"));
//          userService.saveRole(new Role(UUID.randomUUID(),"ROLE_ADMIN"));

          userService.Add(new UserDTO(UUID.fromString("ff76474b-b2b7-4291-bb3d-c67f47395ef6"),"admin@withdrive.com","John","Doe","10-02-1990","Male","+420606058797","5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8"));

          userService.addRoleToUser("admin@withdrive.com","USER_DEFAULT");
        };
    }

}