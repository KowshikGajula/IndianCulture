package com.example.demo.model;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public Trip addTrip(@RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip updatedTrip) {
        return tripRepository.findById(id).map(trip -> {
            trip.setDestinationName(updatedTrip.getDestinationName());
            trip.setTripDuration(updatedTrip.getTripDuration());
            trip.setTouristPlaces(updatedTrip.getTouristPlaces());
            trip.setTripCost(updatedTrip.getTripCost());
            trip.setItineraryDetails(updatedTrip.getItineraryDetails());
            trip.setAccommodationDetails(updatedTrip.getAccommodationDetails());
            trip.setTravelDetails(updatedTrip.getTravelDetails());
            trip.setContactDetails(updatedTrip.getContactDetails());
            trip.setWebsiteLink(updatedTrip.getWebsiteLink());
            trip.setBookingInfo(updatedTrip.getBookingInfo());
            trip.setGroupSize(updatedTrip.getGroupSize());
            trip.setImageLink(updatedTrip.getImageLink());
            return tripRepository.save(trip);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripRepository.deleteById(id);
    }
}