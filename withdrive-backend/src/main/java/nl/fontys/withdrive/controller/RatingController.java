package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import nl.fontys.withdrive.interfaces.service.IRatingService;
import nl.fontys.withdrive.interfaces.service.ITripService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rating")
public class RatingController {
    private final IRatingService ratings;
    private final ITripService trips;
    private final IUserService users;

    @Autowired
    public RatingController(IRatingService ratings, ITripService trips,IUserService users){
        this.ratings = ratings;
        this.trips = trips;
        this.users = users;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postRating(@RequestBody RatingDTO rating) {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        rating.setRater(loggedInUser.getUserID());
        rating.setUser(trips.RetrieveByNumber(rating.getTrip()).getDriver().getUserID());
        ratings.add(rating);
    }

    @GetMapping("/avg/{userID}")
    public float getRatingsByUser(@PathVariable UUID userID) {
        return ratings.averageRatingUser(userID);
    }

    @GetMapping("/check/{tripID}")
    @ResponseStatus(HttpStatus.OK)
    public boolean hasUserRatedTrip(@PathVariable UUID tripID) {
        UserDTO loggedInUser = this.users.retrieveByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return ratings.hasReviewed(tripID,loggedInUser.getUserID());
    }


}
