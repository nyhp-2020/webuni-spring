package hu.webuni.hr.nyhp.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Start Tomcat on a random port
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient(timeout = "36000")
public class EmployeeControllerIT {
	
	private static final String BASE_URI = "/api/employees";
	

	@Autowired
	WebTestClient webTestClient;
	
//	@Autowired
//	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
//	@Autowired
//	PositionRepository positionRepository;

	String username = "user2";
	String password = "pass";
	
	@BeforeEach
	public void init() {
		String username = "user2";
		if(employeeRepository.findByUsername(username).isEmpty()) {
			Employee employee = new Employee();
			employee.setUsername(username);
			employee.setPassword(passwordEncoder.encode(password));
			employeeRepository.save(employee);
		}
	}
	
	@Test
	void testModifyEmployee() throws Exception {
		LocalDateTime ldt = LocalDateTime.of(2019, 9, 14, 0, 0, 0, 0);
		//Employee e1 = new Employee(1, "Peter", "Manager", 10000, ldt);
		EmployeeDto employeeDto1 = new EmployeeDto(1, "Peter", "Manager", 10000, ldt);
		EmployeeDto savedDto = createEmployee(employeeDto1);
		
		EmployeeDto employeeDtoBefore = getEmployeeById(savedDto.getId());

		ldt = LocalDateTime.of(2022, 3, 31, 0, 0, 0, 0);
		EmployeeDto employeeDto2 = new EmployeeDto(1, "Peter Ny. H.", "Manager", 20000, ldt);
		EmployeeDto modifiedDto = modifyEmployee(savedDto.getId(), employeeDto2);
		
		EmployeeDto employeeDtoAfter = getEmployeeById(modifiedDto.getId());
		
//		assertThat(employeeDto1)
		assertThat(savedDto)
		.usingRecursiveComparison()
		.isEqualTo(employeeDtoBefore);
		
//		assertThat(employeeDto2)
		assertThat(modifiedDto)
		.usingRecursiveComparison()
		.isEqualTo(employeeDtoAfter);
		
		ldt = LocalDateTime.of(2022, 3, 31, 0, 0, 0, 0);
		EmployeeDto employeeDto3 = new EmployeeDto(1, "Peter Ny. H.", "Manager", 0, ldt);
		modifyEmployeeWithInvalidData(savedDto.getId(), employeeDto3);//salary should be positive
	}
	
//	@Test
//	void testFindEmployeesByExample() throws Exception {
//		
//		//Employee example = new Employee();
//		
//		List<Employee> employees = employeeRepository.findAll();
//		
//		Employee example = employees.get(0);
//		
//		//List<Position> positions = positionRepository.findByName("Manager");
//		//Position pos = positions.get(0);
//		//pos.addEmployee(example);
//		//example.setPos(pos);
//		//positionRepository.save(pos);
//		System.out.println(example.getId());
//		//List<Employee> foundEmployees = employeeService.findEmployeeByExample(example);
//		assertThat(getClass());
//	}

	private void modifyEmployeeWithInvalidData(long id,EmployeeDto employeeDto) {
		webTestClient
		.put()
		.uri(BASE_URI+"/"+id)
		.headers(headers -> headers.setBasicAuth(username, password))
		.bodyValue(employeeDto)
		.exchange()
		.expectStatus()
		.isBadRequest();
	}

	private EmployeeDto getEmployeeById(long id) {
		return webTestClient
		.get()
		.uri(BASE_URI+"/"+id)
		.headers(headers -> headers.setBasicAuth(username, password))
		.exchange()
		.expectStatus()
		.isOk().expectBody(EmployeeDto.class)
		.returnResult()
		.getResponseBody();
	}

	private EmployeeDto modifyEmployee(long id,EmployeeDto employeeDto) {
		return webTestClient
		.put()
		.uri(BASE_URI+"/"+id)
		.headers(headers -> headers.setBasicAuth(username, password))
		.bodyValue(employeeDto)
		.exchange()
		.expectStatus()
		.isOk().expectBody(EmployeeDto.class)
		.returnResult()
		.getResponseBody();
		
	}

	private EmployeeDto createEmployee(EmployeeDto employeeDto) {
		return webTestClient
		.post()
		.uri(BASE_URI)
		.headers(headers -> headers.setBasicAuth(username, password))
		.bodyValue(employeeDto)
		.exchange()
		.expectStatus()
		.isOk().expectBody(EmployeeDto.class)
		.returnResult()
		.getResponseBody();
	}
}
