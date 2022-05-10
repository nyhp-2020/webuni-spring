package hu.webuni.hr.nyhp.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.RequestBody;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.repository.HolidayRepository;
import hu.webuni.hr.nyhp.service.EmployeeService;
import hu.webuni.hr.nyhp.service.HolidayService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HolidayControllerIT {
	
	private static final String BASE_URI = "/api/holidays";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	HolidayService holidayService;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	EmployeeService employeeService;

	@Test
	void testCreateHoliday() throws Exception {
		List<Employee> employees = employeeService.findAll();
		List<Long> ids = new ArrayList<>();
		
		long clid1=employees.get(0).getId();
		ids.add(clid1);
		LocalDate startd = LocalDate.of(2022,5,10);
		LocalDate endd = LocalDate.of(2022, 5, 15);
		createHoliday(clid1,startd,endd);
		
		long clid2=employees.get(1).getId();
		ids.add(clid2);
		startd = LocalDate.of(2022,5,12);
		endd = LocalDate.of(2022, 5, 13);
		createHoliday(clid2,startd,endd);
		
		List<Holiday> holidays = holidayRepository.findAll();
		System.out.println(holidays.size());
		assertThat(ids.contains(holidays.get(0).getClaimer().getId()));
		assertThat(ids.contains(holidays.get(1).getClaimer().getId()));
	}
	
	
	@Test
	void testFindHolidaysByExample() throws Exception {
		List<Employee> employees = employeeService.findAll();
		Employee employee = employees.get(0);
		HolidayDto holidayDto = new HolidayDto();
		Holiday holiday = new Holiday();
		holiday.setApproved(true);
		holiday.setApprover(employee);
		holidayService.findHolidayByExample(holiday);
		
		
	}
	
	private void createHoliday(long clid,LocalDate start, LocalDate end ) {
		webTestClient
		.get()
		.uri(BASE_URI+"/"+clid+"/from/"+start+"/to/"+end)
		.exchange()
		.expectStatus()
		.isOk();
	} 
	
	private List<HolidayDto> findHolidayByExample(HolidayDto holidayDto){
		return webTestClient
		.post()
		.uri(BASE_URI+"/example")
		.bodyValue(holidayDto)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBodyList(HolidayDto.class)
		.returnResult()
		.getResponseBody();	
		
	}

}
