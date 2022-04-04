package hu.webuni.hr.nyhp.service;

import org.springframework.beans.factory.annotation.Value;

import hu.webuni.hr.nyhp.model.Employee;

public class DefaultEmployeeService extends EmployeeService {
	
	@Value("${hr.employee.def.percent}")
	private int defaultPercent;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return defaultPercent;
	}

}
