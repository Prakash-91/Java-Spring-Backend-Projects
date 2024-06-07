package com.prakash.flightservices.controller;

import com.prakash.flightservices.dto.PassengerDto;
import com.prakash.flightservices.dto.ReservationDto;
import com.prakash.flightservices.entity.Flight;
import com.prakash.flightservices.entity.Passenger;
import com.prakash.flightservices.entity.Reservation;
import com.prakash.flightservices.repository.FlightRepository;
import com.prakash.flightservices.repository.PassengerRepository;
import com.prakash.flightservices.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReservationRestController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = "/flights")
    public List<Flight> findFlights() {
        return flightRepository.findAll();
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public Flight findFlightsById(@PathVariable("id") int id) {
        return flightRepository.findById(id).get();
    }

    @RequestMapping(value = "/flightsSearch")
    public List<Flight> findFlightsSearch(@RequestParam("from") String from,
                                          @RequestParam("to") String to,
                                          @RequestParam("departureDate")
                                          @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate) {
        return flightRepository.findFlights(from, to, departureDate);
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    @Transactional
    public Reservation saveReservation(@RequestBody PassengerDto passengerDto) {
        Flight flight = flightRepository.findById(passengerDto.getFlightId()).get();
        System.out.println("Flight information is : "+flight);
        Passenger passengerBuilder =
                Passenger.builder().firstName(passengerDto.getPassengerFirstName())
                        .middleName(passengerDto.getPassengerMiddleName())
                        .lastName(passengerDto.getPassengerLastName())
                        .phone(passengerDto.getPassengerPhone())
                        .email(passengerDto.getPassengerEmail())
                        .build();
        Passenger passenger = passengerRepository.save(passengerBuilder);
        Reservation reservation = Reservation.builder().flight(flight).passenger(passenger).checkedIn(false).build();
        return reservationRepository.save(reservation);
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
    public Reservation findReservation(@PathVariable("id") int id) {
        return reservationRepository.findById(id).get();
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public Reservation updateReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId()).get();
        reservation.setCheckedIn(reservationDto.getCheckedIn());
        reservation.setNumberOfBags(reservationDto.getNumberOfBags());
        return reservationRepository.save(reservation);
    }

}

