package hu.webuni.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.airport.model.Flight;

public interface FlighttRepository extends JpaRepository<Flight, Long> {

	
}
