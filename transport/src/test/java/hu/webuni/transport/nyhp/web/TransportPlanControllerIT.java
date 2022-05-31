package hu.webuni.transport.nyhp.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.transport.nyhp.dto.DelayRequestDto;
import hu.webuni.transport.nyhp.model.Address;
import hu.webuni.transport.nyhp.model.Milestone;
import hu.webuni.transport.nyhp.model.Section;
import hu.webuni.transport.nyhp.model.TransportPlan;
import hu.webuni.transport.nyhp.repository.AddressRepository;
import hu.webuni.transport.nyhp.repository.MilestoneRepository;
import hu.webuni.transport.nyhp.repository.SectionRepository;
import hu.webuni.transport.nyhp.repository.TransportPlanRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Start Tomcat on a random port
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient(timeout = "36000")
public class TransportPlanControllerIT {
	private static final String BASE_URI = "/api/transportPlans";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	AddressRepository addressRepository;
	
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	
	@Test
	void testSetDelay() {
		Address address1 = new Address(0,"HU","8000","Székesfehérvár","Kossuth","5",0.0,0.0);
		Address address2 = new Address(0,"HU","8000","Székesfehérvár","Budai","100",0.0,0.0);
		Address address3 = new Address(0,"HU","1135","Budapest","Lehel","20",0.0,0.0);
		Address address4 = new Address(0,"HU","1118","Budapest","Etele","3",0.0,0.0);
		
		long aid1 = addressRepository.save(address1).getId();
		long aid2 = addressRepository.save(address2).getId();
		long aid3 = addressRepository.save(address3).getId();
		long aid4 = addressRepository.save(address4).getId();
		
		address1 = addressRepository.getReferenceById(aid1);
		address2 = addressRepository.getReferenceById(aid2);
		address3 = addressRepository.getReferenceById(aid3);
		address4 = addressRepository.getReferenceById(aid4);
		
		LocalDateTime ldt1 = LocalDateTime.of(2022, 5, 31, 0, 0, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2022, 6, 01, 0, 0, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2022, 6, 05, 0, 0, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2022, 6, 15, 0, 0, 0, 0);
		
		
		Milestone milestone1 =new Milestone(0,address1,ldt1);
		Milestone milestone2 =new Milestone(0,address1,ldt2);
		Milestone milestone3 =new Milestone(0,address1,ldt3);
		Milestone milestone4 =new Milestone(0,address1,ldt4);
		
		long mid1 = milestoneRepository.save(milestone1).getId();
		long mid2 = milestoneRepository.save(milestone2).getId();
		long mid3 = milestoneRepository.save(milestone3).getId();
		long mid4 = milestoneRepository.save(milestone4).getId();
		
		milestone1 = milestoneRepository.getReferenceById(mid1);
		milestone2 = milestoneRepository.getReferenceById(mid2);
		milestone3 = milestoneRepository.getReferenceById(mid3);
		milestone4 = milestoneRepository.getReferenceById(mid4);
		
		Section section1 = new Section();
		section1.setFromMilestone(milestone1);
		section1.setToMilestone(milestone2);
		section1.setNumber(0);
		
		Section section2 = new Section();
		section2.setFromMilestone(milestone3);
		section2.setToMilestone(milestone4);
		section2.setNumber(1);
		
		section1 = sectionRepository.save(section1);
		section2 = sectionRepository.save(section2);
		
		List<Section> sections = new ArrayList<>();
		sections.add(section1);
		sections.add(section2);
		
		TransportPlan transportPlan = new TransportPlan(0,1000,sections);
		transportPlan = transportPlanRepository.save(transportPlan);
		
		long beforeIncome = transportPlan.getIncome();
		
		long tpid = transportPlan.getId();
		
		DelayRequestDto delayRequestDto = new DelayRequestDto(0,mid2,65);
		
		setDelay(tpid,delayRequestDto);
		
		long afterIncome = transportPlanRepository.getReferenceById(tpid).getIncome();
		
		System.out.println(afterIncome);
		
		
	}
	
	private void setDelay(long id,DelayRequestDto delayRequestDto) {
		webTestClient
		.put()
		.uri(BASE_URI+"/"+id+"/delay")
		.bodyValue(delayRequestDto)
		.exchange()
		.expectStatus()
		.isOk();
	}

}
