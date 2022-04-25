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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyControllerIT {

	private static final String BASE_URI = "/api/companies";

	List<CompanyDto> companies = new ArrayList<>();
	List<EmployeeDto> employees = new ArrayList<>();

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testDeleteEmployee() throws Exception {
		companies = getAllCompanies();
		for(CompanyDto company:companies)
			System.out.println(company.getId());
			
			
		long companyId = companies.get(0).getId();
		//System.out.println(companyId);
		List<EmployeeDto> employeesOfCompany = companies.get(0).getEmployees();
		int en1 = employeesOfCompany.size();
		System.out.println(en1);
		long employeeId = employeesOfCompany.get(0).getId();
		//System.out.println(employeeId);
		deleteEmployee(companyId, employeeId);

		CompanyDto company2 = getById(companyId);
		int en2 = company2.getEmployees().size();
		
		assertThat(en1-1 == en2);

	}

//	@Test
//	void testAddNewEmployee() throws Exception {
//
//	}
//
//	@Test
//	void testChangeEmployeeList() throws Exception {
//
//	}

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
		webTestClient.get()
		.uri(BASE_URI + "/employee" + "?coid="
		+ companyId 
		+ "&emid=" + employeeId)
		.exchange()
		.expectStatus()
		.isOk();
	}

	private CompanyDto getById(long companyId) {
		return webTestClient.get()
		.uri(BASE_URI +"/" + companyId)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(CompanyDto.class)
		.returnResult()
		.getResponseBody();	
	}
}
