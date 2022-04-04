package hu.webuni.hr.nyhp.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.hr.nyhp.config.HrConfigProperties;
import hu.webuni.hr.nyhp.model.Employee;

public class SmartEmployeeService extends EmployeeService {
	
	@Autowired
	HrConfigProperties conf;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		Period period = Period.between(employee.getStartd().toLocalDate(), LocalDateTime.now().toLocalDate());

		Duration duration = Duration.between(employee.getStartd(), LocalDateTime.now());
		double diffyears = duration.toDays() / 365.0;

		if (diffyears < conf.getSmart().getLimit1())
			return conf.getSmart().getPercent1();
		else if (diffyears < conf.getSmart().getLimit2())
			return conf.getSmart().getPercent2();
		else if (diffyears < conf.getSmart().getLimit3())
			return conf.getSmart().getPercent3();
		else if (diffyears >= conf.getSmart().getLimit3())
			return conf.getSmart().getPercent4();
		else
			return 0;
	}

}
