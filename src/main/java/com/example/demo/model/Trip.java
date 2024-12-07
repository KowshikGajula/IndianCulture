package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trips") // This annotation tells Hibernate to create a table named 'trips'
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination_name", nullable = false)
    private String destinationName;

    @Column(name = "trip_duration", nullable = false)
    private String tripDuration;

    @Column(name = "tourist_places", nullable = false)
    private String touristPlaces;

    @Column(name = "trip_cost", nullable = false)
    private String tripCost;

    @Column(name = "itinerary_details", nullable = false)
    private String itineraryDetails;

    @Column(name = "accommodation_details", nullable = false)
    private String accommodationDetails;

    @Column(name = "travel_details", nullable = false)
    private String travelDetails;

    @Column(name = "contact_details", nullable = false)
    private String contactDetails;

    @Column(name = "website_link", nullable = false)
    private String websiteLink;

    @Column(name = "booking_info", nullable = false)
    private String bookingInfo;

    @Column(name = "group_size", nullable = false)
    private String groupSize;

    @Column(name = "image_link", nullable = true)
    private String imageLink; // For storing the image URL

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        this.tripDuration = tripDuration;
    }

    public String getTouristPlaces() {
        return touristPlaces;
    }

    public void setTouristPlaces(String touristPlaces) {
        this.touristPlaces = touristPlaces;
    }

    public String getTripCost() {
        return tripCost;
    }

    public void setTripCost(String tripCost) {
        this.tripCost = tripCost;
    }

    public String getItineraryDetails() {
        return itineraryDetails;
    }

    public void setItineraryDetails(String itineraryDetails) {
        this.itineraryDetails = itineraryDetails;
    }

    public String getAccommodationDetails() {
        return accommodationDetails;
    }

    public void setAccommodationDetails(String accommodationDetails) {
        this.accommodationDetails = accommodationDetails;
    }

    public String getTravelDetails() {
        return travelDetails;
    }

    public void setTravelDetails(String travelDetails) {
        this.travelDetails = travelDetails;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
