package hu.webuni.hr.nyhp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.nyhp.service.DefaultEmployeeService;
import hu.webuni.hr.nyhp.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {
	@Bean
	public EmployeeService employeeService(){
		return new DefaultEmployeeService();
	}
}
