package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.tripApplication.ApplicationRequestDTO;
import nl.fontys.withdrive.dto.tripApplication.ApplicationResponseDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.enumeration.ApplicationStatus;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trip/app")
public class ApplicationController {
    private final IApplicationService applications;
    private final IUserService users;

    @Autowired
    public ApplicationController(IApplicationService applications,IUserService users){
        this.applications = applications;
        this.users = users;
    }

    @GetMapping("t/{tripID}")
    public ResponseEntity<List<ApplicationResponseDTO>> GetAppByTrip(@PathVariable(value = "tripID") UUID id){
        List<ApplicationResponseDTO> apps = this.applications.RetrieveByTripID(id);
        if(apps!=null){
            return ResponseEntity.ok().body(apps);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity ("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("u")
    public ResponseEntity<List<ApplicationResponseDTO>> GetAppByUser(){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<ApplicationResponseDTO> apps = this.applications.RetrieveByUserID(loggedInUser.getUserID());
        if(apps!=null){
            return ResponseEntity.ok().body(apps);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("u/active")
    public ResponseEntity<List<ApplicationResponseDTO>> GetActiveAppByUser(){
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<ApplicationResponseDTO> apps = this.applications.RetrieveActiveByUserID(loggedInUser.getUserID());
        if(apps!=null){
            return ResponseEntity.ok().body(apps);
        }
        //return ResponseEntity.notFound().build();
        return new ResponseEntity("Please provide a valid user number.", HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<ApplicationRequestDTO> MakeApplication(@RequestBody ApplicationRequestDTO app) {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        applications.Add(app,loggedInUser.getUserID());
        String text = "Your application for trip " + app.getTrip() + " has been submitted!";
        return new ResponseEntity(text,HttpStatus.CREATED);
    }

    @PostMapping("/accept")
    public ResponseEntity<ApplicationRequestDTO> AcceptApplication(@RequestBody ApplicationRequestDTO app) {
        applications.RespondToApplication(app,ApplicationStatus.ACCEPTED);
        String text = app.getUser() + "'s application for trip " + app.getTrip() + " has been accepted!";
        return new ResponseEntity(text,HttpStatus.CREATED);
    }

    @PostMapping("/reject")
    public ResponseEntity<ApplicationRequestDTO> RejectApplication(@RequestBody ApplicationRequestDTO app) {
        app.setStatus(ApplicationStatus.REJECTED);
        applications.RespondToApplication(app,ApplicationStatus.REJECTED);
        String text = app.getUser() + "'s application for trip " + app.getTrip() + " has been rejected!";
        return new ResponseEntity(text,HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ApplicationRequestDTO> UpdateApplication(@RequestBody ApplicationRequestDTO app){
        applications.Update(app);
        String text = "Your application for trip " + app.getTrip() + " has been updated!";
        return new ResponseEntity(text,HttpStatus.CREATED);
    }
}
