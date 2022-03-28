package hu.webuni.hr.nyhp.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.service.SalaryService;

@RestController
@RequestMapping("/api/employees")
public class HrController {

	private Map<Long, EmployeeDto> employees = new HashMap<>();

	{
		employees.put(1L, new EmployeeDto(1, "Peter", "Manager", 10000, LocalDateTime.of(2021, 3, 7, 0, 0, 0, 0)));
		employees.put(2L,
				new EmployeeDto(2, "Paul", "Department leader", 9000, LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0)));
		employees.put(3L, new EmployeeDto(3, "Tim", "Manager", 11000, LocalDateTime.of(2015, 1, 9, 0, 0, 0, 0)));
	}

	@GetMapping
	public List<EmployeeDto> getAll() {
		return new ArrayList<>(employees.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		if (!employees.containsKey(id))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(employees.get(id));
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		long id = employeeDto.getId();
		if (!employees.containsKey(id)) {
			employees.put(id, employeeDto);
			return employeeDto;
		} else
			return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if (!employees.containsKey(id))
			return ResponseEntity.notFound().build();

		employeeDto.setId(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employees.remove(id);
	}

	@GetMapping("/salary")
	public List<EmployeeDto> getListOfHigherSalary(@RequestParam("salary") int salary) {
		// System.out.println(salary);
		return (new ArrayList<>(employees.values())).stream().filter(e -> e.getSalary() > salary)
				.collect(Collectors.toList());
	}

}
