package hu.webuni.hr.nyhp.service;

import org.springframework.beans.factory.annotation.Value;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

public class DefaultEmployeeService extends EmployeeService {
	
//	public DefaultEmployeeService(EmployeeRepository employeeRepository) {
//		super(employeeRepository);
//		// TODO Auto-generated constructor stub
//	}

	@Value("${hr.employee.def.percent}")
	private int defaultPercent;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return defaultPercent;
	}

}
