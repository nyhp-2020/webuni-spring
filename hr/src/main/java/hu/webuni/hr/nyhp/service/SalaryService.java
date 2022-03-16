package hu.webuni.hr.nyhp.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.model.Employee;

@Service
public class SalaryService {

	private EmployeeService employeeservice;

	public SalaryService(EmployeeService employeeService) {
		// super();
		this.employeeservice = employeeService;
	}

	public int getNewSalary(Employee employee) {
		return (int) (employee.getSalary() * (100.0 + employeeservice.getPayRaisePercent(employee)) / 100);
	}

}
