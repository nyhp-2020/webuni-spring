package hu.webuni.hr.nyhp.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.nyhp.dto.CompanyDto;
import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.mapper.EmployeeMapper;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyControllerIT {

	private static final String BASE_URI = "/api/companies";

	List<CompanyDto> companies = new ArrayList<>();
	List<EmployeeDto> employees = new ArrayList<>();
	
	EmployeeDto employeeDto;
	long employeeId;

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@Test
	void testDeleteEmployee() throws Exception {
		companies = getAllCompanies();
			
		long companyId = companies.get(0).getId();
		List<EmployeeDto> employeesOfCompany = companies.get(0).getEmployees();
		int en1 = employeesOfCompany.size();
		employeeDto = employeesOfCompany.get(0);
		employeeId = employeeDto.getId();

		deleteEmployee(companyId, employeeId);

		CompanyDto company2 = getById(companyId);
		int en2 = company2.getEmployees().size();
	
		assertThat(en1-1 == en2);

	}

	@Test
	void testAddNewEmployee() throws Exception {
		companies = getAllCompanies();
		employees = getEmployeesWithNullCompany();
		long companyId = companies.get(1).getId();
		//List<EmployeeDto> employeesOfCompany = companies.get(1).getEmployees();
		
		CompanyDto company1 = getById(companyId);
		int size1 = company1.getEmployees().size();
		EmployeeDto emp = employees.size() > 0 
				? addEmployee(employees.get(0), companyId)
				: null;
		
		CompanyDto company2 = getById(companyId);
		int size2 = company2.getEmployees().size();
		assertThat(size1 + 1 == size2);
		assertThat(company1.equals(company2));
	}

	@Test
	void testChangeEmployeeList() throws Exception {
		companies = getAllCompanies();
		long companyId = companies.get(1).getId();
		CompanyDto company1 = getById(companyId);
		employees = company1.getEmployees();
		
		companyId = companies.get(0).getId();
		CompanyDto company2 = getById(companyId);
		
		List<EmployeeDto> emps = changeEmployeeList(employees,companyId);

		assertThat(getById(companyId).getEmployees())
		.usingRecursiveFieldByFieldElementComparator() //compare fields of object
		.containsExactlyElementsOf(employees);
	}


	private List<CompanyDto> getAllCompanies() {
		List<CompanyDto> responseList = webTestClient
				.get()
				.uri(BASE_URI + "?full=true")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(CompanyDto.class)
				.returnResult()
				.getResponseBody();
		Collections.sort(responseList, (a1, a2) -> Long.compare(a1.getId(), a2.getId()));
		return responseList;
	}

	private void deleteEmployee(long companyId, long employeeId) {
		webTestClient.delete()
		.uri(BASE_URI + "/employee" + "?coid="
		+ companyId 
		+ "&emid=" + employeeId)
		.exchange()
		.expectStatus()
		.isOk();
	}

	private CompanyDto getById(long companyId) {
		return webTestClient.get()
		.uri(BASE_URI +"/" + companyId+"?full=true")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(CompanyDto.class)
		.returnResult()
		.getResponseBody();	
	}
	
	private EmployeeDto addEmployee(EmployeeDto empDto, long companyId) {
		//System.out.println(empDto);
		return webTestClient
		.post()
		.uri(BASE_URI + "/employee" + "?coid=" + companyId)
		.bodyValue(empDto)
		//.body(BodyInserters.fromValue(empDto))
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(EmployeeDto.class)
		.returnResult()
		.getResponseBody();	
	}
	
	private List<EmployeeDto> getEmployeesWithNullCompany() {
		List<Employee> empnullcomp = employeeRepository.findByCompanyIsNull();
		return employeeMapper.employeesToDtos(empnullcomp);
	}
	
	private List<EmployeeDto> changeEmployeeList(List<EmployeeDto> newemployees, long coid) {
		List<EmployeeDto> responseList = webTestClient
		.put()
		.uri(BASE_URI + "/employee" + "?coid=" + coid)
		.bodyValue(newemployees)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBodyList(EmployeeDto.class)
		.returnResult()
		.getResponseBody();
		Collections.sort(responseList, (a1, a2) -> Long.compare(a1.getId(), a2.getId()));
		return responseList;
	}
}
