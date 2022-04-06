package hu.webuni.airport.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.airport.dto.AirportDto;
import hu.webuni.airport.mapper.AirportMapper;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.service.AirportService;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

	@Autowired
	AirportService airportService;

	@Autowired
	AirportMapper airportMapper;

	@GetMapping
	public List<AirportDto> getAll() {
		return airportMapper.airportsToDtos(airportService.findAll());
	}

	@GetMapping("/{id}")
	public AirportDto getById(@PathVariable long id) {
		Airport airport = airportService.findById(id);

		if (airport != null)
			return airportMapper.airportToDto(airport);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Springboot default mechanism
	}
	
	@PostMapping
	public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto/*, BindingResult errors*/) {
		Airport airport = airportService.save(airportMapper.dtoToAirport(airportDto));
		return airportMapper.airportToDto(airport);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
		Airport airport = airportMapper.dtoToAirport(airportDto);
		airport.setId(id);
		AirportDto savedAirportDto = airportMapper.airportToDto(airportService.update(airport));
		return ResponseEntity.ok(savedAirportDto);
		
//		if(!airports.containsKey(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		checkUniqueIata(airportDto.getIata());
//		airportDto.setId(id); // url is primary
//		airports.put(id, airportDto);
//		return ResponseEntity.ok(airportDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAirport(@PathVariable long id) {
		airportService.delete(id);
//		airports.remove(id);
	}

//	private Map<Long, AirportDto> airports = new HashMap<>();
//
//	{
//		airports.put(1L, new AirportDto(1, "abc", "XYZ"));
//		airports.put(2L, new AirportDto(2, "def", "UVW"));
//	}

//	@GetMapping
//	public List<AirportDto> getAll() {
//		return new ArrayList<>(airports.values());
//	}
//
//	@GetMapping("/{id}")
////	public ResponseEntity<AirportDto> getById(@PathVariable long id) {
//	public AirportDto getById(@PathVariable long id) {
//		AirportDto airportDto = airports.get(id);
////		if (airportDto != null)
////			return ResponseEntity.ok(airportDto); // status code ok
////		else
////			return ResponseEntity.notFound().build();
//		if(airportDto != null)
//			return airportDto;
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND); //Springboot default mechanism
//	}
//
//	@PostMapping
//	public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto/*, BindingResult errors*/) {
//		
////		if(errors.hasErrors())
////			throw
//		checkUniqueIata(airportDto.getIata());
//		airports.put(airportDto.getId(), airportDto);
//		return airportDto;
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
//		if(!airports.containsKey(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		checkUniqueIata(airportDto.getIata());
//		airportDto.setId(id); // url is primary
//		airports.put(id, airportDto);
//		return ResponseEntity.ok(airportDto);
//	}
//	
//	private void checkUniqueIata(String iata) {
//		Optional<AirportDto> airportWithSameIata = airports.values().stream()
//				.filter(a -> a.getIata().equals(iata))
//				.findAny();
//		if(airportWithSameIata.isPresent())
//			throw new NonUniqueIataException(iata);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAirport(@PathVariable long id) {
//		airports.remove(id);
//	}
//
}
