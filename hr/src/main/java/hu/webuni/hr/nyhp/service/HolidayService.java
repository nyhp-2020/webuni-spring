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

	public List<Holiday> findHolidayByExample(Holiday example) {

		Employee approver = example.getApprover();
		boolean approved = example.getApproved();
		Employee claimer = example.getClaimer();
		LocalDate claimDate = example.getClaimDate();
		LocalDate startDate = example.getStartDate();
		LocalDate endDate = example.getEndDate();
		
		Specification<Holiday> spec = Specification.where(null);
		Specification<Holiday> spec1 = Specification.where(null);
		Specification<Holiday> spec2 = Specification.where(null);
		Specification<Holiday> spec3 = Specification.where(null);
		Specification<Holiday> spec4 = Specification.where(null);
		
		if(approver != null)
			spec = spec.and(HolidaySpecifications.hasApproved(approved));
		
		if (claimer != null && claimer.getName() != null && !claimer.getName().equals("")) {
			spec = spec.and(HolidaySpecifications.hasClaimer(claimer));
		}
		
		if(claimDate != null && startDate != null && endDate != null && startDate.isBefore(endDate))
			spec = spec.and(HolidaySpecifications.hasClaimDate(claimDate, startDate, endDate));
		
		if(claimDate == null && startDate != null && endDate != null && startDate.isBefore(endDate))
			//A.(B+C)
			spec1 = spec1
			.and(HolidaySpecifications.hasStartDate(startDate, endDate));
			spec2 = spec2
			.and(HolidaySpecifications.hasEndDate(startDate, endDate));
			
			spec3 = spec3
					.and(HolidaySpecifications.lessStartDate(startDate));
			spec4 = spec4
					.and(HolidaySpecifications.greaterEndDate(endDate));
					
			spec3 = spec3.and(spec4);
			spec3 = spec3.and(spec);
			
			spec1 = spec1.or(spec2);
			spec1 = spec1.and(spec);
			
			spec = spec1.or(spec3);
			
		
		return holidayRepository.findAll(spec, Sort.by("id"));
	}

	
}
