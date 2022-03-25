package hu.webuni.hr.nyhp.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.dto.CompanyDto;
import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	{
		// companies.put(1L, new CompanyDto(1L, "BME", "Dunapart", null));
		
		LocalDateTime ldt = LocalDateTime.of(2019, 9, 20, 0, 0, 0, 0);
		EmployeeDto employeeDto1 = new EmployeeDto(1, "Robert", "Director", 20000, ldt);
		EmployeeDto employeeDto2 = new EmployeeDto(2, "Paul", "Manager", 10000, ldt);
		CompanyDto companyDto = new CompanyDto(1L, "20-22", "BME", "Dunapart");
		companyDto.addEmployeeDto(employeeDto1);
		companyDto.addEmployeeDto(employeeDto2);
		companies.put(1L, companyDto);
		
		//companies.put(1L, new CompanyDto(1L, "20-22", "BME", "Dunapart", null));
		
	}

	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(required = false) boolean full) {
		if (full)
			return new ArrayList<>(companies.values());
		else
			return companies
					.values()
					.stream()
					.collect(Collectors.toList());
//					.stream()
//					.map(c -> c.setEmployees(null)).collect(Collectors.toList());
//					
					
				
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id, @RequestParam(required = false) boolean full) {
		// System.out.println(full);
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
