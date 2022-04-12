package hu.webuni.hr.nyhp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.dto.CompanyDto;
import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.mapper.CompanyMapper;
import hu.webuni.hr.nyhp.mapper.EmployeeMapper;
import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	EmployeeMapper employeeMapper;

//	@Autowired
//	CompanyRepository companyRepository;

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) boolean full) {
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (full)
			return companyMapper.companyToDto(company);
		else
			return new CompanyDto(companyMapper.companyToDto(company)); // spec Copy Contructor

	}

	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {

		companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		companyDto.setId(id);
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		companyService.delete(id);
	}

	@PostMapping("/employee")
	public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long coid) {
		companyService.findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = companyService.addEmployee(employeeMapper.dtoToEmployee(employeeDto), coid);
		return employeeMapper.employeeToDto(employee);
	}

	@DeleteMapping("/employee")
	public void deleteEmployee(@RequestParam long coid, @RequestParam long emid) {
		companyService.findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		companyService.deleteEmployee(coid, emid);
	}

	@PutMapping("/employee")
	public List<EmployeeDto> changeEmployeeList(@RequestBody List<EmployeeDto> newemployees, @RequestParam long coid) {
		companyService.findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		List<Employee> employeeList = companyService
				.changeEmployeeList(employeeMapper.dtosToEmployees(newemployees),coid);
		return employeeMapper.employeesToDtos(employeeList);
	}

//	private Map<Long, CompanyDto> companies = new HashMap<>();
//
//	{
//		LocalDateTime ldt = LocalDateTime.of(2019, 9, 20, 0, 0, 0, 0);
//		EmployeeDto employeeDto1 = new EmployeeDto(1, "Robert", "Director", 20000, ldt);
//		EmployeeDto employeeDto2 = new EmployeeDto(2, "Paul", "Manager", 10000, ldt);
//		CompanyDto companyDto = new CompanyDto(1L, "20-22", "BME", "Dunapart");
//		companyDto.addEmployeeDto(employeeDto1);
//		companyDto.addEmployeeDto(employeeDto2);
//		companies.put(1L, companyDto);
//	}

	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(required = false) boolean full) {
		List<Company> companies = companyService.findAll();

		if (full)
			return companyMapper.companiesToDtos(companies);
		else {
			List<CompanyDto> compDtoList = new ArrayList<>();
			for (Company company : companies) {
				CompanyDto newcoDto = new CompanyDto(companyMapper.companyToDto(company)); // special copy constructor
				compDtoList.add(newcoDto);
			}
			return compDtoList;
		}

	}

//	@GetMapping("/{id}")
//	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) boolean full) {
//		Company company = companyService.findById(id);
//
//		if (company != null) {
//			if (full)
//				return companyMapper.companyToDto(company);
//			else
//				return new CompanyDto(companyMapper.companyToDto(company)); // spec Copy Contructor
//		} else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}

//	@PostMapping("/employee")
//	public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long coid) {
//
//		Company company = companyService.findById(coid);
//
//		if (company != null) {
//			Employee employee = companyService.addEmployee(employeeMapper.dtoToEmployee(employeeDto), coid);
//			return employeeMapper.employeeToDto(employee);
//		} else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//	}

//	@PutMapping("/{id}")
//	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
//
//		Company company = companyService.findById(id);
//
//		if (company != null) {
//			companyDto.setId(id);
//			return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
//		} else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//	}

//	@DeleteMapping("/{id}")
//	public void deleteCompany(@PathVariable long id) {
//		Company company = companyService.findById(id);
//
//		if (company != null)
//			companyService.delete(id);
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//	}

//	@DeleteMapping("/employee")
//	public void deleteEmployee(@RequestParam long coid, @RequestParam long emid) {
//		Company company = companyService.findById(coid);
//
//		if (company != null)
//			companyService.deleteEmployee(coid, emid);
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//	}

//	@PutMapping("/employee")
//	public List<EmployeeDto> changeEmployeeList(@RequestBody List<EmployeeDto> newemployees, @RequestParam long coid) {
//		Company company = companyService.findById(coid);
//
//		if (company != null) {
//			List<Employee> employeeList = companyService
//					.changeEmployeeList(employeeMapper.dtosToEmployees(newemployees), coid);
//			return employeeMapper.employeesToDtos(employeeList);
//		} else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//	}

//	@GetMapping
//	public List<CompanyDto> getAll(@RequestParam(required = false) boolean full) {
//		if (full)
//			return new ArrayList<>(companies.values());
//		else {
//			List<CompanyDto> complist = new ArrayList<>();
//			for (CompanyDto companyDto : companies.values()) {
//				CompanyDto newcoDto = new CompanyDto(companyDto); // special copy constructor
//				complist.add(newcoDto);
//			}
//			return complist;
//		}
////			return companies
////					.values()
////					.stream()
////					.collect(Collectors.toList());				
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<CompanyDto> getById(@PathVariable long id, @RequestParam(required = false) boolean full) {
//		if (!companies.containsKey(id))
//			return ResponseEntity.notFound().build();
//
//		if (full)
//			return ResponseEntity.ok(companies.get(id));
//		else
//			return ResponseEntity.ok(new CompanyDto(companies.get(id)));
//	}
//
//	@PostMapping
//	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
//		long id = companyDto.getId();
//		if (!companies.containsKey(id)) {
//			companies.put(id, companyDto);
//			return companyDto;
//		} else
//			return null;
//	}
//
//	@PostMapping("/employee")
//	public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam long coid) {
//		if (!companies.containsKey(coid)) {
//			return null;
//		} else {
//			companies.get(coid).addEmployeeDto(employeeDto);
//			return employeeDto;
//		}
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
//		if (!companies.containsKey(id))
//			return ResponseEntity.notFound().build();
//
//		companyDto.setId(id);
//		companies.put(id, companyDto);
//		return ResponseEntity.ok(companyDto);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteCompany(@PathVariable long id) {
//		companies.remove(id);
//	}
//	
//	@DeleteMapping("/employee")
//	public void deleteEmployee(@RequestParam long coid,@RequestParam long emid) {
//		companies.get(coid).delEmployeeDto(emid);
//	}
//	
//	@PutMapping("/employee")
//	public List<EmployeeDto> changeEmployeeList(@RequestBody List<EmployeeDto> newemployees,@RequestParam long coid) {
//		companies.get(coid).setEmployees(newemployees);
//		return newemployees;
//	}
//	

}
