package nl.fontys.withdrive;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.enumeration.TripStatus;
import nl.fontys.withdrive.service.TripService;
import nl.fontys.withdrive.service.UserService;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.UUID;

@EnableScheduling
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
    CommandLineRunner run(UserService userService, TripService tripService){
        return args -> {
          userService.saveRole(new Role(UUID.fromString("81b83fb2-0727-4404-8cf2-1f73feeb2980"),"ROLE_DEFAULT"));
          userService.saveRole(new Role(UUID.fromString("90d87f90-f1a3-4e92-863d-5468247dcb14"),"ROLE_USER"));
          userService.saveRole(new Role(UUID.fromString("a5c7c167-7fe0-4f2b-867a-55e3c0aeb5ab"),"ROLE_ADMIN"));

          userService.Add(new UserDTO(UUID.fromString("7d013b4a-8061-45d9-b0f4-47897e219901"),"admin@withdrive.com","John","Doe","10-02-1990","male","+420606058797","password",null));
          userService.Add(new UserDTO(UUID.fromString("d1ec1b55-3297-445e-be4f-a31ddc342ad7"),"driver@withdrive.com","John","Doe","10-02-1990","male","+420606058797","password",null));
          userService.Add(new UserDTO(UUID.fromString("c88ec1bb-974b-48bf-9306-ff3fa7827e80"),"passenger@withdrive.com","Maria","Jopek","10-02-1990","female","+420606058797","password",null));

          userService.addRoleToUser("admin@withdrive.com","ROLE_ADMIN");

          tripService.Add(new TripRequestDTO(UUID.randomUUID(),"Eindhoven","Amsterdam","Quick trip","2021-12-16T13:30","12-ABC-55",2,10,UUID.fromString("d1ec1b55-3297-445e-be4f-a31ddc342ad7"), Collections.emptyList(), TripStatus.OPEN));
        };
    }



}