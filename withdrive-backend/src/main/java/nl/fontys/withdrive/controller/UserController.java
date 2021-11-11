package nl.fontys.withdrive.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserManager users;

    @Autowired
    public UserController(IUserManager users){
        this.users = users;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> GetAllUsers(){
        List<UserDTO> list = this.users.RetrieveAll();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{userID}")
    public ResponseEntity<UserDTO> GetUserByNumber(@PathVariable(value = "userID") UUID number){
        UserDTO user = this.users.RetrieveByID(number);
        if(user!=null){
            return ResponseEntity.ok().body(user);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<UserDTO> GetUserCount(){
        List<UserDTO> users = this.users.RetrieveAll();
        return new ResponseEntity(users.size(),HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> CreateUser(@RequestBody UserDTO user) {
//        if(user.getUserID() == null){
//            user.setUserID(UUID.randomUUID());
//        }
        System.out.println(user.getUserID());
        if (!this.users.Add(user)){
            String entity =  "Student with student number " + user.getUserID() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getUserID(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PostMapping("/role/create")
    public ResponseEntity<Role>createRole(@RequestBody Role role){
        String url = "user" + "/" + role.getName(); // url of the created student
        URI uri = URI.create(url);
        return ResponseEntity.created(uri).body(users.saveRole(role));
    }

    @PutMapping()
    public ResponseEntity<UserDTO> UpdateUser(@RequestBody UserDTO user){
        if(this.users.Update(user)){
            String url = "user" + "/" + user.getUserID(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Please provide a valid user number.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{userID}")
    public ResponseEntity<UserDTO> DeleteUser(@PathVariable UUID number) {
        if (this.users.Delete(number)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        String url = "user" + "/" + form.getRoleName(); // url of the created student
        users.addRoleToUser(form.getEmail(),form.getRoleName());
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.FOUND);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorisationHeader = request.getHeader(AUTHORIZATION);
        if(authorisationHeader != null && authorisationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorisationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                UserDTO user = users.retrieveByEmail(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_msg", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token not found");
        }
    }

    @Data
    class RoleToUserForm{
        private String email;
        private String roleName;
    }
}
