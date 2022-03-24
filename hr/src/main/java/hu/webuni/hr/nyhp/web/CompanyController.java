package hu.webuni.hr.nyhp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.dto.CompanyDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	{
		//companies.put(1L, new CompanyDto(1L, "BME", "Dunapart", null));
		companies.put(1L, new CompanyDto(1L,"urtzurtz" ,"BME", "Dunapart", null));
	}

	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<>(companies.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(companies.get(id));
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		long id = companyDto.getId();
		if (!companies.containsKey(id)) {
			companies.put(id, companyDto);
			return companyDto;
		} else
			return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();

		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
}
