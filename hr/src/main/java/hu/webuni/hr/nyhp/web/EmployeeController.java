package hu.webuni.hr.nyhp.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.service.SalaryService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	SalaryService salaryservice;

	@GetMapping
	public int getPayRisePercent(@RequestBody Employee employee) {
		return salaryservice.getPayRaisePercent(employee);
	}

}