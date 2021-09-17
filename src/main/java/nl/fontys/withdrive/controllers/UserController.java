package nl.fontys.withdrive.controllers;

import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.interfaces.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserManager users;

    @Autowired
    public UserController(IUserManager users){
        this.users = users;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> GetAllClients(){
        List<UserDTO> list = this.users.RetrieveAll();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{clientNumber}")
    public ResponseEntity<UserDTO> GetClientByNumber(@PathVariable(value = "clientNumber") int number){
        UserDTO user = this.users.RetrieveByNumber(number);
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
    public ResponseEntity<UserDTO> CreateClient(@RequestBody UserDTO user) {
        if (!this.users.Add(user)){
            String entity =  "Student with student number " + user.getClientNumber() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getClientNumber(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<UserDTO> UpdateClient(@RequestBody UserDTO user){
        if(this.users.Update(user)){
            String url = "user" + "/" + user.getClientNumber(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Please provide a valid user number.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{clientNumber}")
    public ResponseEntity<UserDTO> DeleteClient(@PathVariable int clientNumber) {
        if (this.users.Delete(clientNumber)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
        }
    }
}
