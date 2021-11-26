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
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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
    @PutMapping("/update")
    public ResponseEntity<TripRequestDTO> UpdateTrip(@RequestBody TripRequestDTO trip){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        trip.setDriver(loggedInUser.getUserID());
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
    public ResponseEntity<?> DeleteTrip(@PathVariable UUID tripNumber) {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (this.trips.Delete(tripNumber,loggedInUser.getUserID())) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid trip number.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<TripResponseDTO>> GetTripsByUser(){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveTripsByUser(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/active/driver")
    public ResponseEntity<List<TripResponseDTO>> GetTripsByDriver() {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveTripsByDriver(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }
}
