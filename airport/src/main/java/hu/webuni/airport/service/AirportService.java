package hu.webuni.airport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	LogEntryService logEntryService;

//Injektálás konstruktorral
	public AirportService(AirportRepository airportRepository, FlighttRepository flightRepository,
			LogEntryService logEntryService) {
		super();
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
		this.logEntryService = logEntryService;
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
		if (airportRepository.existsById(airport.getId())) {
			logEntryService.createLog(
					String.format("Airport modified with id %d new name is %s ", airport.getId(), airport.getName()));
//			callBackendSystem();
			return airportRepository.save(airport); // insert,update
		} else
			throw new NoSuchElementException();
	}

	private void callBackendSystem() {

		if (new Random().nextInt(4) == 1)
			throw new RuntimeException();
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
	public Flight createFlight(String flightNumber, long takeoffAirportId, long landingAirportId,
			LocalDateTime takeoffDateTime) {
		Flight flight = new Flight();
		flight.setFlightNumber(flightNumber);
		flight.setTakeoff(airportRepository.findById(takeoffAirportId).get());
		flight.setLanding(airportRepository.findById(landingAirportId).get());
		flight.setTakeoffTime(takeoffDateTime);
		return flightRepository.save(flight);
	}

	public List<Flight> findFlightsByExample(Flight example) {

		long id = example.getId();
		String flightNumber = example.getFlightNumber();
		String takeoffIata = null;
		Airport takeoff = example.getTakeoff();
		if (takeoff != null)
			takeoffIata = takeoff.getIata();
		LocalDateTime takeoffTime = example.getTakeoffTime();

		Specification<Flight> spec = Specification.where(null);

		if (id > 0) {
			spec = spec.and(FlightSpecifications.hasId(id));
		}

		if (StringUtils.hasText(flightNumber))
			spec = spec.and(FlightSpecifications.hasFlightNumber(flightNumber));

		if (StringUtils.hasText(takeoffIata))
			spec = spec.and(FlightSpecifications.hasTakeoffIata(takeoffIata));

		if (takeoffTime != null)
			spec = spec.and(FlightSpecifications.hasTakeoffTime(takeoffTime));

		return flightRepository.findAll(spec, Sort.by("id"));
	}
}
