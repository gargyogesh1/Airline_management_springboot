package com.example.flight_service.service;

import com.example.flight_service.entity.Flight;
import com.example.flight_service.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Create Flight
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Get All Flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get Flight by ID
    public Optional<Flight> getFlightById(UUID id) {
        return flightRepository.findById(id);
    }

    // Update Flight
    public Flight updateFlight(UUID id, Flight updatedFlight) {
        return flightRepository.findById(id)
                .map(existing -> {
                    existing.setCarrier(updatedFlight.getCarrier());
                    existing.setOrigin(updatedFlight.getOrigin());
                    existing.setDestination(updatedFlight.getDestination());
                    existing.setDepartureTime(updatedFlight.getDepartureTime());
                    existing.setArrivalTime(updatedFlight.getArrivalTime());
                    existing.setSeatCapacity(updatedFlight.getSeatCapacity());
                    return flightRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    // Delete Flight
    public void deleteFlight(UUID id) {
        flightRepository.deleteById(id);
    }
}
