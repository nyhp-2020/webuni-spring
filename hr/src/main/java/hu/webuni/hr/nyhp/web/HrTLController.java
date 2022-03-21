package hu.webuni.hr.nyhp.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.nyhp.model.Employee;



@Controller
public class HrTLController {

	private List<Employee> allEmployees = new ArrayList<>();

	{
		
		//LocalDateTime ldt = LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0);
		Employee e1 = new Employee(1, "Peter", "Manager", 10000, LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0));
		allEmployees.add(e1);
		//allEmployees.add(new Employee());
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String,Object> model) {
		model.put("employees", allEmployees);
		model.put("newEmployee", new Employee(0,"","",0,LocalDateTime.now()));
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		allEmployees.add(employee);
		return "redirect:employees";
	}
}
