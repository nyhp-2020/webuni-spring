package hu.webuni.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Airport;

@Service
public class AirportService {

//	private Map<Long, Airport> airports = new HashMap<>();
//
//	{
//		airports.put(1L, new Airport(1, "abc", "XYZ"));
//		airports.put(2L, new Airport(2, "def", "UVW"));
//	}
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata(), null);
		em.persist(airport);
		// airports.put(airport.getId(), airport);
		return airport;
	}

	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata(), airport.getId());
		return em.merge(airport);
		// airports.put(airport.getId(), airport);
		// return airport;
	}

	private void checkUniqueIata(String iata, Long id) {
		boolean forUpdate = id != null;
		TypedQuery<Long> query = em
				.createNamedQuery(forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata", Long.class)
				.setParameter("iata", iata);
		if(forUpdate)
			query.setParameter("id", id);
		
		Long count = query.getSingleResult();
//		Optional<Airport> airportWithSameIata = airports.values().stream().filter(a -> a.getIata().equals(iata))
//				.findAny();
//		if (airportWithSameIata.isPresent())
		if (count > 0)
			throw new NonUniqueIataException(iata);
	}

	public List<Airport> findAll() {
		return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
//		return new ArrayList<>(airports.values());
	}

	public Airport findById(long id) {
		return em.find(Airport.class, id);
//		return airports.get(id);
	}

	@Transactional
	public void delete(long id) {
		em.remove(findById(id));
//		airports.remove(id);
	}

}
