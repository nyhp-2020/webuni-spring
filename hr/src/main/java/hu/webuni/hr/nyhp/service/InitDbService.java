package hu.webuni.hr.nyhp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.CompanyType;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Position;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.repository.CompanyTypeRepository;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;
import hu.webuni.hr.nyhp.repository.HolidayRepository;
import hu.webuni.hr.nyhp.repository.PositionRepository;

@Service
public class InitDbService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyTypeRepository companytypeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public void clearDB() {
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		positionRepository.deleteAll();
		companytypeRepository.deleteAll();
		holidayRepository.deleteAll();
	}
	
	@Transactional
	public void insertTestData() {
		CompanyType ct = new CompanyType();
		ct.setName("BT");
		companytypeRepository.save(ct);
		
		ct = new CompanyType();
		ct.setName("KFT");
		companytypeRepository.save(ct);
		
		ct = new CompanyType();
		ct.setName("ZRT");
		companytypeRepository.save(ct);
		
		ct = new CompanyType();
		ct.setName("NYRT");
		companytypeRepository.save(ct);
		
		Position pos=new Position();
		pos.setName("Developer");
		positionRepository.save(pos);
		
		pos=new Position();
		pos.setName("Tester");
		positionRepository.save(pos);
		
		pos=new Position();
		pos.setName("Manager");
		positionRepository.save(pos);
		
		
		Employee employee1 = new Employee();
		employee1.setName("Test1");
		//employee.setPosition("Manager");
//		List<Position> positions = positionRepository.findByName("Manager");
//		pos = positions.get(0);
//		employee.setPos(pos);
		employee1.setSalary(10000);
		employee1.setStartd(LocalDateTime.of(2021, 4, 10, 10, 0, 0));
		employee1.setUsername("boss");
		employee1.setPassword(passwordEncoder.encode("pass"));
		employee1.setRoles(Set.of("user"));
		//employee.setSuperior(null);
		employeeRepository.save(employee1);
//		//Employee boss = employeeRepository.findByUsername("boss").orElseThrow(()-> new UsernameNotFoundException("boss"));
//		
		Employee employee2 = new Employee();
		employee2.setName("Test2");
		//employee.setPosition("Broker");
//		positions = positionRepository.findByName("Developer");
//		pos = positions.get(0);
//		employee.setPos(pos);
		employee2.setSalary(5000);
		employee2.setStartd(LocalDateTime.of(2022, 4, 3, 0, 0, 0));
		employee2.setUsername("user2");
		employee2.setPassword(passwordEncoder.encode("pass"));
		employee2.setRoles(Set.of("user"));
		//employee.setSuperior(boss);
		employeeRepository.save(employee2);
		
		Employee employee3 = new Employee();
		employee3.setName("Test3");
		//employee.setPosition("Inspector");
//		positions = positionRepository.findByName("Manager");
//		pos = positions.get(0);
//		employee.setPos(pos);
		employee3.setSalary(7000);
		employee3.setStartd(LocalDateTime.of(2020, 6, 5, 0, 0, 0));
		employee3.setUsername("user3");
		employee3.setPassword(passwordEncoder.encode("pass"));
		employee3.setRoles(Set.of("admin","user"));
		//employee.setSuperior(boss);
		employeeRepository.save(employee3);
		
		employee1.addManagedEmployee(employee2);
		employee1.addManagedEmployee(employee3);
		
		
		Company company = new Company();
		company.setRegistry("ABC01");
		company.setName("Company1");
		company.setAddress("Budapest1");
		//company.setType(companytypeRepository.findByName("KFT"));
		companyRepository.save(company);
		
		company = new Company();
		company.setRegistry("BBB01");
		company.setName("Company2");
		company.setAddress("Budapest2");
		//company.setType(companytypeRepository.findByName("BT"));
		companyRepository.save(company);
		
//		List<Employee> employees = employeeRepository.findAll();
//		employee = employees.get(0);
//		
//		List<Company> companies = companyRepository.findAll();
//		company = companies.get(0);
//		
//		companyService.addEmployee(employee, company.getId());
//		
//		List<Position> positions = positionRepository.findByName("Manager");
//		pos = positions.get(0);
//		pos.addEmployee(employee);
//		positionRepository.save(pos);
//		
//		ct= companytypeRepository.findByName("KFT");
//		ct.addCompany(company);
//		companytypeRepository.save(ct);
//		
//		
//		
//		company = companies.get(1);
//		employee = employees.get(1);
//		
//		companyService.addEmployee(employee, company.getId());
//		
//		positions = positionRepository.findByName("Developer");
//		pos = positions.get(0);
//		pos.addEmployee(employee);
//		positionRepository.save(pos);
//		
//		ct= companytypeRepository.findByName("BT");
//		ct.addCompany(company);
//		companytypeRepository.save(ct);
//		
//		employee = employees.get(2);
//		
//		companyService.addEmployee(employee, company.getId());
//		
//		positions = positionRepository.findByName("Developer");
//		pos = positions.get(0);
//		pos.addEmployee(employee);
//		positionRepository.save(pos);
	}
	
	@Transactional
	public void setConnections() {
		List<Employee> employees = employeeRepository.findAll();
				
		List<Company> companies = companyRepository.findAll();
		Company company = companies.get(0);
		
		List<Position> positions = positionRepository.findByName("Manager");
		Position manager = positions.get(0);
		
		positions = positionRepository.findByName("Developer");
		Position developer = positions.get(0);
		
		positions = positionRepository.findByName("Tester");
		Position tester = positions.get(0);
		
		Employee boss = employeeRepository.findByUsername("boss").orElseThrow(()-> new UsernameNotFoundException("boss"));
		manager.addEmployee(boss);
		//boss.setRoles(Set.of("user"));
		
		for(Employee employee:employees) {
			employee.setSuperior(boss);
			companyService.addEmployee(employee, company.getId());
			if(employee.getUsername().equals("user3")) {
				employee.setRoles(Set.of("admin","user"));
				developer.addEmployee(employee);	
			}else if(!employee.getUsername().equals("boss")) {
				employee.setRoles(Set.of("user"));
				tester.addEmployee(employee);
			}else
				employee.setRoles(Set.of("user"));	
		}	
	}
}
