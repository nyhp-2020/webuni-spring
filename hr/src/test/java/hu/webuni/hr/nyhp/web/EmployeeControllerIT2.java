package hu.webuni.hr.nyhp.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;
import hu.webuni.hr.nyhp.repository.PositionRepository;
import hu.webuni.hr.nyhp.service.EmployeeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Start Tomcat on a random port
public class EmployeeControllerIT2 {
	
	private static final String BASE_URI = "/api/employees";
	

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
//	@Autowired
//	PositionRepository positionRepository;

//	@Test
//	void testModifyEmployee() throws Exception {
//		LocalDateTime ldt = LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0);
//		//Employee e1 = new Employee(1, "Peter", "Manager", 10000, ldt);
//		EmployeeDto employeeDto1 = new EmployeeDto(1, "Peter", "Manager", 10000, ldt);
//		createEmployee(employeeDto1);
//		
//		EmployeeDto employeeDtoBefore = getEmployeeById();
//
//		ldt = LocalDateTime.of(2022, 3, 31, 0, 0, 0, 0);
//		EmployeeDto employeeDto2 = new EmployeeDto(1, "Peter Ny. H.", "Manager", 20000, ldt);
//		modifyEmployee(employeeDto2);
//		
//		EmployeeDto employeeDtoAfter = getEmployeeById();
//		
//		assertThat(employeeDto1)
//		.usingRecursiveComparison()
//		.isEqualTo(employeeDtoBefore);
//		
//		assertThat(employeeDto2)
//		.usingRecursiveComparison()
//		.isEqualTo(employeeDtoAfter);
//		
//		ldt = LocalDateTime.of(2022, 3, 31, 0, 0, 0, 0);
//		EmployeeDto employeeDto3 = new EmployeeDto(1, "Peter Ny. H.", "Manager", 0, ldt);
//		modifyEmployeeWithInvalidData(employeeDto3);//salary should be positive
//	}
	
	@Test
	void testFindEmployeesByExample() throws Exception {
		
		//Employee example = new Employee();
		
		List<Employee> employees = employeeRepository.findAll();
		
		Employee example = employees.get(2);
		long id = example.getId();
		System.out.println(id);
//		example.setId(0);
//		//example.setName("TES");
//		example.setName(null);
//		example.setPos(null);
//		example.setSalary(0);
//		example.setStartd(null);
//		example.setCompany(null);
		
		
		//List<Position> positions = positionRepository.findByName("Manager");
		//Position pos = positions.get(0);
		//pos.addEmployee(example);
		//example.setPos(pos);
		//positionRepository.save(pos);

		List<Employee> foundEmployees = employeeService.findEmployeeByExample(example);
		System.out.println(foundEmployees.size());
		assertThat(foundEmployees.get(0).getId()).isEqualTo(id);
	}

//	private void modifyEmployeeWithInvalidData(EmployeeDto employeeDto) {
//		webTestClient
//		.put()
//		.uri(BASE_URI+"/1")
//		.bodyValue(employeeDto)
//		.exchange()
//		.expectStatus()
//		.isBadRequest();
//	}
//
//	private EmployeeDto getEmployeeById() {
//		return webTestClient
//		.get()
//		.uri(BASE_URI+"/1")
//		.exchange()
//		.expectStatus()
//		.isOk().expectBody(EmployeeDto.class)
//		.returnResult()
//		.getResponseBody();
//	}
//
//	private void modifyEmployee(EmployeeDto employeeDto) {
//		webTestClient
//		.put()
//		.uri(BASE_URI+"/1")
//		.bodyValue(employeeDto)
//		.exchange()
//		.expectStatus()
//		.isOk();
//		
//	}
//
//	private void createEmployee(EmployeeDto employeeDto) {
//		webTestClient
//		.post()
//		.uri(BASE_URI)
//		.bodyValue(employeeDto)
//		.exchange()
//		.expectStatus()
//		.isOk();
//	}
}
