package hu.webuni.hr.nyhp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.service.DefaultEmployeeServiceOld;
import hu.webuni.hr.nyhp.service.EmployeeServiceOld;
import hu.webuni.hr.nyhp.service.InitDbService;
import hu.webuni.hr.nyhp.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryservice;
	
	@Autowired
	InitDbService initdbservice;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LocalDateTime ldt = LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0);
		Employee e1 = new Employee(1, "Peter", "Manager", 10000, ldt);

		ldt = LocalDateTime.of(2019, 9, 20, 0, 0, 0, 0);
		Employee e2 = new Employee(2, "Paul", "Manager", 10000, ldt);

		System.out.println(salaryservice.getNewSalary(e1));
		System.out.println(salaryservice.getNewSalary(e2));

		Period p = Period.between(ldt.toLocalDate(), LocalDate.now());
		System.out.println(p);
		System.out.println(p.getYears());
		System.out.println(p.getMonths());
		System.out.println(p.getDays());

		Duration d = Duration.between(ldt, LocalDateTime.now());
		System.out.println(d.toDays() / 365.0);
		
		initdbservice.clearDB();
		initdbservice.insertTestData();
		initdbservice.setConnections();
	}

//	@Bean
//	public EmployeeService employeeService(){
//		return new DefaultEmployeeService();
//	}

}
