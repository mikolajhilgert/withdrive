package nl.fontys.withdrive.controllers;

import nl.fontys.withdrive.dto.TripDTO;
import nl.fontys.withdrive.dto.UserDTO;
import nl.fontys.withdrive.dto.viewmodels.TripVM;
import nl.fontys.withdrive.interfaces.services.ITripManager;
import nl.fontys.withdrive.interfaces.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final ITripManager trips;

    @Autowired
    public TripController(ITripManager trips){
        this.trips = trips;
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> GetAllClients(){
        List<TripDTO> list = this.trips.RetrieveAll();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{tripNumber}")
    public ResponseEntity<TripDTO> GetTripByID(@PathVariable(value = "tripNumber") int number){
        TripDTO user = this.trips.RetrieveByNumber(number);
        if(user!=null){
            return ResponseEntity.ok().body(user);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<TripVM> CreateTrip(@RequestBody TripVM trip) {
        if (!this.trips.Add(trip)){
            String entity =  "Trip with TripID " + trip.getTripID() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "trip" + "/" + trip.getTripID(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<TripVM> UpdateTrip(@RequestBody TripVM trip){
        if(this.trips.Update(trip)){
            String url = "user" + "/" + trip.getTripID(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Please provide a valid user number.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{tripNumber}")
    public ResponseEntity<UserDTO> DeleteClient(@PathVariable int tripNumber) {
        if (this.trips.Delete(tripNumber)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid trip number.", HttpStatus.NOT_FOUND);
        }
    }


}
