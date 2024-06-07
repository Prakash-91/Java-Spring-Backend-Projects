package com.prakash.flightservices.repository;

import com.prakash.flightservices.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
}