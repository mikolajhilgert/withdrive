package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.service.UserManager;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserManager userService){
        return args -> {
          userService.saveRole(new Role(UUID.fromString("81b83fb2-0727-4404-8cf2-1f73feeb2980"),"ROLE_DEFAULT"));
          userService.saveRole(new Role(UUID.fromString("90d87f90-f1a3-4e92-863d-5468247dcb14"),"ROLE_USER"));
          userService.saveRole(new Role(UUID.fromString("a5c7c167-7fe0-4f2b-867a-55e3c0aeb5ab"),"ROLE_ADMIN"));

          userService.Add(new UserDTO(UUID.fromString("ff76474b-b2b7-4291-bb3d-c67f47395ef6"),"admin@withdrive.com","John","Doe","10-02-1990","Male","+420606058797","password"));

          userService.addRoleToUser("admin@withdrive.com","ROLE_ADMIN");
        };
    }

}