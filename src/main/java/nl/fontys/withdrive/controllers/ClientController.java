package nl.fontys.withdrive.controllers;

import nl.fontys.withdrive.dto.ClientDTO;
import nl.fontys.withdrive.interfaces.ClientSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientSupplier clients;

    @Autowired
    public ClientController(ClientSupplier clients){
        this.clients = clients;
    }
    @GetMapping
    public ResponseEntity<List<ClientDTO>> GetAllClients(){
        List<ClientDTO> list = this.clients.RetrieveAll();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{clientNumber}")
    public ResponseEntity<ClientDTO> GetClientByNumber(@PathVariable(value = "clientNumber") int number){
        ClientDTO route = this.clients.RetrieveByNumber(number);
        if(route!=null){
            return ResponseEntity.ok().body(route);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> CreateClient(@RequestBody ClientDTO client) {
        if (!this.clients.Add(client)){
            String entity =  "Student with student number " + client.getClientNumber() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "client" + "/" + client.getClientNumber(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<ClientDTO> UpdateClient(@RequestBody ClientDTO client){
        if(this.clients.Update(client)){
            return ResponseEntity.noContent().build();
        }
        else{
            return new ResponseEntity("Please provide a valid route ID.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{clientNumber}")
    public ResponseEntity DeleteClient(@PathVariable int clientNumber){
        this.clients.Delete(clientNumber);
        return ResponseEntity.ok().build();
    }
}
