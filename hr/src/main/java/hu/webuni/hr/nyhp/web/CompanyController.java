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
		LocalDateTime ldt = LocalDateTime.of(2019, 9, 20, 0, 0, 0, 0);
		EmployeeDto employeeDto1 = new EmployeeDto(1, "Robert", "Director", 20000, ldt);
		EmployeeDto employeeDto2 = new EmployeeDto(2, "Paul", "Manager", 10000, ldt);
		CompanyDto companyDto = new CompanyDto(1L, "20-22", "BME", "Dunapart");
		companyDto.addEmployeeDto(employeeDto1);
		companyDto.addEmployeeDto(employeeDto2);
		companies.put(1L, companyDto);
	}

	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(required = false) boolean full) {
		if (full)
			return new ArrayList<>(companies.values());
		else {
			List<CompanyDto> complist = new ArrayList<>();
			for (CompanyDto companyDto : companies.values()) {
				CompanyDto newcoDto = new CompanyDto(companyDto); // special copy constructor
				complist.add(newcoDto);
			}
			return complist;
		}
//			return companies
//					.values()
//					.stream()
//					.collect(Collectors.toList());				
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id, @RequestParam(required = false) boolean full) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();

		if (full)
			return ResponseEntity.ok(companies.get(id));
		else
			return ResponseEntity.ok(new CompanyDto(companies.get(id)));
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

	@PostMapping("/employee")
	public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long coid) {
		if (!companies.containsKey(coid)) {
			return null;
		} else {
			companies.get(coid).addEmployeeDto(employeeDto);
			return employeeDto;
		}
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
	
	@DeleteMapping("/employee")
	public void deleteEmployee(@RequestParam long coid,@RequestParam long emid) {
		companies.get(coid).delEmployeeDto(emid);
	}
	
	@PutMapping("/employee")
	public List<EmployeeDto> changeEmployeeList(@RequestBody List<EmployeeDto> newemployees,@RequestParam long coid) {
		companies.get(coid).setEmployees(newemployees);
		return newemployees;
	}
	

}
