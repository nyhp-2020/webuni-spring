package hu.webuni.hr.nyhp.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.config.HrConfigProperties;
import hu.webuni.hr.nyhp.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

//	@Value("${hr.employee.smart.percent1}")
//	private int smartPercent1;
//
//	@Value("${hr.employee.smart.percent2}")
//	private int smartPercent2;
//
//	@Value("${hr.employee.smart.percent3}")
//	private int smartPercent3;
//
//	@Value("${hr.employee.smart.percent4}")
//	private int smartPercent4;
//
//	@Value("${hr.employee.smart.limit1}")
//	private int smartLimit1;
//
//	@Value("${hr.employee.smart.limit2}")
//	private int smartLimit2;
//
//	@Value("${hr.employee.smart.limit3}")
//	private int smartLimit3;

	@Autowired
	HrConfigProperties conf;

	@Override
	public int getPayRaisePercent(Employee employee) {

		Period period = Period.between(employee.getStartd().toLocalDate(), LocalDateTime.now().toLocalDate());

		Duration duration = Duration.between(employee.getStartd(), LocalDateTime.now());
		double diffyears = duration.toDays() / 365.0;

		// version 1
//		if (period.getYears() < 2)
//			return 0;
//		else if(period.getYears()==2 )
//			return period.getMonths() < 6 ? 0 : 2;
//		else if (period.getYears() > 2 && period.getYears() < 5)
//			return 2;
//		else if (period.getYears() >= 5 && period.getYears() < 10)
//			return 5;
//		else if (period.getYears() >= 10)
//			return 10;
//		else
//			return 0;

		// Version 2. limit1=2, limit2=5
//		if (period.getYears() < smartLimit1)
//			return smartPercent1;
//		else if(period.getYears()==smartLimit1 )
//			return period.getMonths() < 6 ? smartPercent1 : smartPercent2;
//		else if (period.getYears() > smartLimit1 && period.getYears() < smartLimit2)
//			return smartPercent2;
//		else if (period.getYears() >= smartLimit2 && period.getYears() < smartLimit3)
//			return smartPercent3;
//		else if (period.getYears() >= smartLimit3)
//			return smartPercent4;
//		else
//			return 0;

		// Version 3.
//		if (diffyears < 2.5)
//			return 0;
//		else if (diffyears >= 2.5 && diffyears < 5)
//			return 2;
//		else if (diffyears >= 5 && diffyears < 10)
//			return 5;
//		else if (diffyears >= 10)
//			return 10;
//		else
//			return 0;

		// Version 4.
//		if (diffyears < smartLimit1)
//			return smartPercent1;
//		else if (diffyears >= smartLimit1 && diffyears < smartLimit2)
//			return smartPercent2;
//		else if (diffyears >= smartLimit2 && diffyears < smartLimit3)
//			return smartPercent3;
//		else if (diffyears >= smartLimit3)
//			return smartPercent4;
//		else
//			return 0;

		if (diffyears < conf.getSmart().getLimit1())
			return conf.getSmart().getPercent1();
		else if (diffyears >= conf.getSmart().getLimit1() && diffyears < conf.getSmart().getLimit2())
			return conf.getSmart().getPercent2();
		else if (diffyears >= conf.getSmart().getLimit2() && diffyears < conf.getSmart().getLimit3())
			return conf.getSmart().getPercent3();
		else if (diffyears >= conf.getSmart().getLimit3())
			return conf.getSmart().getPercent4();
		else
			return 0;
	}

}
