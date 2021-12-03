package nl.fontys.withdrive.controller;

import nl.fontys.withdrive.dto.rating.RatingDTO;
import nl.fontys.withdrive.interfaces.service.IApplicationService;
import nl.fontys.withdrive.interfaces.service.IRatingService;
import nl.fontys.withdrive.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rating")
public class RatingController {
    private final IRatingService ratings;

    @Autowired
    public RatingController(IRatingService ratings){
        this.ratings = ratings;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postRating(@RequestBody RatingDTO rating) {
        ratings.add(rating);
    }

    @GetMapping("/by/user")
    @ResponseStatus(HttpStatus.OK)
    public void getRatingsByUser(@RequestBody RatingDTO rating) {
        ratings.add(rating);
    }

    @GetMapping("/by/ratertrip")
    @ResponseStatus(HttpStatus.OK)
    public void getRatingsByRater(@RequestBody RatingDTO rating) {
        ratings.add(rating);
    }
}
