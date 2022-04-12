package hu.webuni.hr.nyhp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@Service
public class InitDbService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Transactional
	public void clearDB() {
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}
	
	@Transactional
	public void insertTestData() {
		Employee employee = new Employee();
		employee.setName("Test1");
		employee.setPosition("Manager");
		employee.setSalary(10000);
		employee.setStartd(LocalDateTime.of(2021, 4, 10, 10, 0, 0));
		employeeRepository.save(employee);
		
		List<Employee> employees = employeeRepository.findAll();
		employee = employees.get(0);
		
		Company company = new Company();
		company.setRegistry("ABC01");
		company.setName("Company1");
		company.setAddress("Budapest1");
		companyRepository.save(company);
		
		List<Company> companies = companyRepository.findAll();
		company = companies.get(0);
		
		companyService.addEmployee(employee, company.getId());
	}

}
