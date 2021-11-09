package nl.fontys.withdrive.controller;

import lombok.Data;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.Role;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @Data
    class RoleToUserForm{
        private String email;
        private String roleName;
    }
}
