package hu.webuni.hr.nyhp.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.repository.HolidayRepository;

@Service
public class HolidayService {

	@Autowired
	HolidayRepository holidayRepository;

	@Autowired
	EmployeeService employeeService;

	@Transactional
	public Holiday createHoliday(long clid, LocalDate startDate, LocalDate endDate) {
		Employee employee = employeeService.findById(clid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (endDate.isBefore(startDate))
			throw new EndDateEarlierThanStartDateException();
		Holiday holiday = new Holiday();
		holiday.setClaimer(employee);
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holiday.setClaimDate(LocalDate.now());
		return holidayRepository.save(holiday);
	}

	public List<Holiday> findAll(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Holiday> page = holidayRepository.findAll(pageable);
		List<Holiday> holidays = page.getContent();
		return holidays;
	}

	@Transactional
	public Holiday judgeRequest(long hid, long aid, boolean approved) {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = employeeService.findById(aid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		holiday.setApprover(employee);
		holiday.setApproved(approved);
		return holidayRepository.save(holiday);
	}

	@Transactional
	public Holiday modifyRequest(long hid, long clid, LocalDate startDate, LocalDate endDate) {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = employeeService.findById(clid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(holiday.getApprover() != null)
			throw new RequestAlreadyJudgedException();
		if(holiday.getClaimer().getId() != clid)
			throw new NotOwnRequesException();
		if (endDate.isBefore(startDate))
			throw new EndDateEarlierThanStartDateException();
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holiday.setClaimDate(LocalDate.now());
		return holidayRepository.save(holiday);
	}

	@Transactional
	public void deleteRequest(long hid, long clid) {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = employeeService.findById(clid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(holiday.getApprover() != null)
			throw new RequestAlreadyJudgedException();
		if(holiday.getClaimer().getId() != clid)
			throw new NotOwnRequesException();
		holidayRepository.delete(holiday);	
	}

//	public List<Holiday> findEmployeeByExample(Holiday example) {
//		
//		Employee claimer = example.getClaimer();
//		
//		Specification<Holiday> spec = Specification.where(null);
//		
//		if (claimer != null && claimer.getName() != null && !claimer.getName().equals("")) {
//			spec = spec.and(EmployeeSpecifications.hasClaimer(claimer));
//		}
//		
//		return employeeRepository.findAll(spec, Sort.by("id"));
//	}
	
}
