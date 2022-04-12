package hu.webuni.hr.nyhp.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.mapper.EmployeeMapper;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;
import hu.webuni.hr.nyhp.service.EmployeeService;
import hu.webuni.hr.nyhp.service.SalaryService;

@RestController
//@RequestMapping("/api/employee")
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	SalaryService salaryservice;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/salarypercent")
	public int getPayRisePercent(@RequestBody Employee employee) {
		return salaryservice.getPayRaisePercent(employee);
	}

	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeesToDtos(employeeService.findAll());
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return employeeMapper.employeeToDto(employee);

//		if (employee != null)
//			return employeeMapper.employeeToDto(employee);
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
				employeeDto.setId(id);
				return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));

//		if (employee != null) {
//			employeeDto.setId(id);
//			return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
//		} else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		employeeService.delete(id);
//		if (employee != null)
//			employeeService.delete(id);
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/salary")
	public List<EmployeeDto> getListOfHigherSalary(@RequestParam("salary") int salary) {
		return employeeMapper.employeesToDtos(employeeService.findOfHigherSalary(salary));
	}
	
	@GetMapping("/pos/{position}")
	public List<EmployeeDto> getPosition(@PathVariable String position) {
		
		return employeeMapper.employeesToDtos(employeeRepository.findByPositionLike(position));
	}
	
	@GetMapping("/name/{strg}")
	public List<EmployeeDto> getNameStartWith(@PathVariable String strg) {
		
		return employeeMapper.employeesToDtos(employeeRepository.findByNameStartingWithIgnoreCase(strg));
	}
	
	@GetMapping("/from/{dat1}/to/{dat2}")
	public List<EmployeeDto> getEmployeesBetweenDates(
			@PathVariable
//			@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
			LocalDateTime dat1,
			@PathVariable
//			@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
			LocalDateTime dat2 ) {
		
		return employeeMapper.employeesToDtos(employeeRepository.findByStartdBetween(dat1, dat2));
	}

}