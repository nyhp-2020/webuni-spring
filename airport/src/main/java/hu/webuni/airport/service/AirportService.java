package hu.webuni.airport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlighttRepository;

@Service
public class AirportService {

//	private Map<Long, Airport> airports = new HashMap<>();
//
//	{
//		airports.put(1L, new Airport(1, "abc", "XYZ"));
//		airports.put(2L, new Airport(2, "def", "UVW"));
//	}

	AirportRepository airportRepository;
	FlighttRepository flightRepository;

	public AirportService(AirportRepository airportRepository, FlighttRepository flightRepository) {
		super();
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
	}

//	public AirportService(AirportRepository airportRepository) { //Injection with constructor
//		this.airportRepository = airportRepository;
//	}

//	@PersistenceContext
//	EntityManager em;

	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata(), null);
//		em.persist(airport);
		// airports.put(airport.getId(), airport);
//		return airport;
		return airportRepository.save(airport);
	}

	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata(), airport.getId());
//		return em.merge(airport);
		// airports.put(airport.getId(), airport);
		// return airport;
		if (airportRepository.existsById(airport.getId()))
			return airportRepository.save(airport); // insert,update
		else
			throw new NoSuchElementException();
	}

	private void checkUniqueIata(String iata, Long id) {
		boolean forUpdate = id != null;
//		TypedQuery<Long> query = em
//				.createNamedQuery(forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata", Long.class)
//				.setParameter("iata", iata);
//		if(forUpdate)
//			query.setParameter("id", id);

//		Long count = query.getSingleResult();

//		Optional<Airport> airportWithSameIata = airports.values().stream().filter(a -> a.getIata().equals(iata))
//				.findAny();
//		if (airportWithSameIata.isPresent())

		Long count = forUpdate ? airportRepository.countByIataAndIdNot(iata, id) : airportRepository.countByIata(iata);

		if (count > 0)
			throw new NonUniqueIataException(iata);
	}

	public List<Airport> findAll() {
//		return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
//		return new ArrayList<>(airports.values());
		return airportRepository.findAll();
	}

	public Optional<Airport> findById(long id) {
//		return em.find(Airport.class, id);
//		return airports.get(id);
		return airportRepository.findById(id);
	}

	@Transactional
	public void delete(long id) {
//		em.remove(findById(id));
//		airports.remove(id);
		airportRepository.deleteById(id);
	}

	@Transactional
	public void createFlight() {
		Flight flight = new Flight();
		flight.setFlightNumber("ztertze");
		flight.setTakeoff(airportRepository.findById(1L).get());
		flight.setLanding(airportRepository.findById(3L).get());
		flight.setTakeoffTime(LocalDateTime.of(2021, 4, 10, 10, 0, 0));
		flightRepository.save(flight);
	}
}
