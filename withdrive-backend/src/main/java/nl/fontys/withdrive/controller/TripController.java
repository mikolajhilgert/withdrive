package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.service.ITripService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ITripService trips;
    private final IUserService users;

    @Autowired
    public TripController(ITripService trips, IUserService users){
        this.trips = trips;
        this.users= users;
    }

    @GetMapping("/allActive/{page}")
    public ResponseEntity<List<TripResponseDTO>> getActiveTrips(@PathVariable(value = "page") Integer page){
        List<TripResponseDTO> list = this.trips.retrieveActiveTrips(page);
        if(list.size()>0)
            return ResponseEntity.ok().body(list);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("count")
    public ResponseEntity<Integer> getActiveTrips(){
        return ResponseEntity.ok().body(this.trips.retrieveActiveTripsCount());
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
    public ResponseEntity<List<TripResponseDTO>> GetActiveTripsByUser(){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveActiveTripsByUser(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/active/{origin}")
    public ResponseEntity<List<TripResponseDTO>> GetActiveTripsByOrigin(@PathVariable String origin){
        List<TripResponseDTO> trips = this.trips.retrieveActiveTripsByOrigin(origin);
        if(trips!=null){
            return ResponseEntity.ok().body(trips);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/active/driver")
    public ResponseEntity<List<TripResponseDTO>> GetActiveTripsByDriver() {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveActiveTripsByDriver(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allu")
    public ResponseEntity<List<TripResponseDTO>> GetTripsByUser(){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveTripsByUser(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/alld")
    public ResponseEntity<List<TripResponseDTO>> GetTripsByDriver() {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TripResponseDTO> trip = this.trips.retrieveTripsByDriver(loggedInUser.getUserID());
        if(trip!=null){
            return ResponseEntity.ok().body(trip);
        }
        return new ResponseEntity("Please provide a valid number.", HttpStatus.NOT_FOUND);
    }
}
