package hu.webuni.hr.nyhp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.nyhp.service.EmployeeService;
import hu.webuni.hr.nyhp.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartConfiguration {
//	@Bean
//	public EmployeeServiceOld employeeService(){
//		return new SmartEmployeeServiceOld();
//	}
	
	@Bean
	public EmployeeService employeeService(){
		return new SmartEmployeeService();
	}
}
