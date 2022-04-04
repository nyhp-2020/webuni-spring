package hu.webuni.hr.nyhp.web;

import java.util.List;

import javax.validation.Valid;

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

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.mapper.EmployeeMapper;
import hu.webuni.hr.nyhp.model.Employee;
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
		Employee employee = employeeService.findById(id);

		if (employee != null)
			return employeeMapper.employeeToDto(employee);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.findById(id);

		if (employee != null) {
			employeeDto.setId(id);
			return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Employee employee = employeeService.findById(id);

		if (employee != null)
			employeeService.delete(id);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/salary")
	public List<EmployeeDto> getListOfHigherSalary(@RequestParam("salary") int salary) {
		return employeeMapper.employeesToDtos(employeeService.findOfHigherSalary(salary));
	}

}