package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.trip.TripRequestDTO;
import nl.fontys.withdrive.dto.trip.TripResponseDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.service.IApplicationManager;
import nl.fontys.withdrive.interfaces.service.ITripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trip/app")
public class ApplicationController {
    private final IApplicationManager applications;

    @Autowired
    public ApplicationController(IApplicationManager applications){
        this.applications = applications;
    }

    @GetMapping("t/{tripID}")
    public ResponseEntity<List<ApplicationResponseDTO>> GetAppByTrip(@PathVariable(value = "tripID") UUID id){
        List<ApplicationResponseDTO> apps = this.applications.RetrieveByTripID(id);
        if(apps!=null){
            return ResponseEntity.ok().body(apps);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("u/{userID}")
    public ResponseEntity<List<ApplicationResponseDTO>> GetAppByUser(@PathVariable(value = "userID") UUID id){
        List<ApplicationResponseDTO> apps = this.applications.RetrieveByUserID(id);
        if(apps!=null){
            return ResponseEntity.ok().body(apps);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/apply")
//    public ResponseEntity<ApplicationRequestDTO> CreateTrip(@RequestBody ApplicationRequestDTO app) {
//        if (!this.trips.Add(trip)){
//            String entity =  "Trip with TripID " + trip.getTripID() + " already exists, or you tried to apply to your own trip.";
//            return new ResponseEntity(entity,HttpStatus.CONFLICT);
//        } else {
//            String url = "trip" + "/" + trip.getTripID(); // url of the created trip
//            URI uri = URI.create(url);
//            return new ResponseEntity(uri,HttpStatus.CREATED);
//        }
//    }

}
