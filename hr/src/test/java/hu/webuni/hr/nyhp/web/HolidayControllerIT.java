package hu.webuni.hr.nyhp.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.reactive.server.WebTestClient;

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
		//System.out.println(holidays.size());
		assertThat(ids.contains(holidays.get(0).getClaimer().getId()));
		assertThat(ids.contains(holidays.get(1).getClaimer().getId()));
	}
	
	
	@Test
	void testFindHolidaysByExample() throws Exception {
		List<Employee> employees = employeeService.findAll();
		Employee employee = employees.get(0);
		
		long clid1=employees.get(0).getId();

		LocalDate startd = LocalDate.of(2022,5,10);
		LocalDate endd = LocalDate.of(2022, 5, 15);
		createHoliday(clid1,startd,endd);
		
		long clid2=employees.get(1).getId();

		startd = LocalDate.of(2022,5,13);
		endd = LocalDate.of(2022, 5, 13);
		createHoliday(clid2,startd,endd);
		
		List<Holiday> holidays = holidayRepository.findAll();
		//System.out.println(holidays.size());

		Holiday holiday = holidays.get(0);
		//holiday.setApproved(false);
		holiday.setApprover(null);
		holiday.setClaimer(null);
		holiday.setClaimDate(null);
		holiday.setStartDate(LocalDate.of(2022, 5, 11));
		holiday.setEndDate(LocalDate.of(2022, 5, 14));
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
		
		Page<Holiday> foundHolidays = holidayService.findHolidayByExample(holiday, pageable);
		assertThat(foundHolidays.getContent().size()== 2);
		System.out.println(foundHolidays.getContent().size());
//		System.out.println(foundHolidays.getContent().get(0).getStartDate());
//		System.out.println(foundHolidays.getContent().get(0).getEndDate());
		
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
