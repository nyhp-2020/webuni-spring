package hu.webuni.hr.nyhp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {
	
	@Value("${hr.employee.def.percent}")
	private int defaultPercent;

	@Override
	public int getPayRaisePercent(Employee employee) {

		//return 5;
		return defaultPercent;
	}

}
