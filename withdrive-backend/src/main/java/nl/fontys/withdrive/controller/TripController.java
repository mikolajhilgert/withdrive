package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.service.ITripManager;
import nl.fontys.withdrive.interfaces.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/trip")
public class TripController {
    private final ITripManager trips;
    private final IUserManager users;

    @Autowired
    public TripController(ITripManager trips, IUserManager users){
        this.trips = trips;
        this.users= users;
    }

    @GetMapping()
    public ResponseEntity<List<TripResponseDTO>> getActiveTrips(){
        List<TripResponseDTO> list = this.trips.retrieveActiveTrips();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TripResponseDTO>> getAllTrips(){
        List<TripResponseDTO> list = this.trips.RetrieveAll();
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{tripNumber}")
    public ResponseEntity<TripResponseDTO> GetTripByID(@PathVariable(value = "tripNumber") UUID number){
        TripResponseDTO trip = this.trips.RetrieveByNumber(number);
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<TripRequestDTO> CreateTrip(@RequestBody TripRequestDTO trip) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO loggedInUser = this.users.retrieveByEmail(authentication.getName());
        trip.setDriver(loggedInUser.getUserID());
        if (!this.trips.Add(trip)){
            String entity =  "Trip with TripID " + trip.getTripID() + " already exists, or you tried to apply to your own trip.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "trip" + "/" + trip.getTripID(); // url of the created trip
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity<TripRequestDTO> UpdateTrip(@RequestBody TripRequestDTO trip){
        if(this.trips.Update(trip)){
            String url = "trip" + "/" + trip.getTripID(); // url of the edited trip
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Please provide a valid trip number.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{tripNumber}")
    public ResponseEntity<UserDTO> DeleteClient(@PathVariable UUID tripNumber) {
        if (this.trips.Delete(tripNumber)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid trip number.", HttpStatus.NOT_FOUND);
        }
    }


}
