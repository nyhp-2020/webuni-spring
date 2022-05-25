package hu.webuni.hr.nyhp.service;

import java.nio.file.AccessDeniedException;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.model.HrUser;
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
	public Holiday judgeRequest(long hid, long aid, boolean approved) throws AccessDeniedException {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		long approverId =holiday.getClaimer().getSuperior().getId();
		if(approverId != aid || getCurrentUser().getEmployee().getId() != aid)
			throw new AccessDeniedException("Approver is not the superior of claimer or not current user");
		Employee employee = employeeService.findById(aid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

//		setApprove(holiday,employee,approved,approverId);
		holiday.setApprover(employee);
		holiday.setApproved(approved);
		return holidayRepository.save(holiday);
	}
	
//	public void setApprove(Holiday holiday, Employee employee, boolean approved,long id) {
//		holiday.setApprover(employee);
//		holiday.setApproved(approved);
//	}

	@Transactional
	public Holiday modifyRequest(long hid, long clid, LocalDate startDate, LocalDate endDate) {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = employeeService.findById(clid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (holiday.getApprover() != null)
			throw new RequestAlreadyJudgedException();
		if (holiday.getClaimer().getId() != clid)
			throw new NotOwnRequesException();
		try {
			checkOwnerOfHolidayRequest(holiday);
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		}
		if (endDate.isBefore(startDate))
			throw new EndDateEarlierThanStartDateException();
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holiday.setClaimDate(LocalDate.now());
		return holidayRepository.save(holiday);
	}

	@Transactional
	public void deleteRequest(long hid, long clid) throws AccessDeniedException {
		Holiday holiday = holidayRepository.findById(hid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Employee employee = employeeService.findById(clid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (holiday.getApprover() != null)
			throw new RequestAlreadyJudgedException();
		if (holiday.getClaimer().getId() != clid)
			throw new NotOwnRequesException();
		
		checkOwnerOfHolidayRequest(holiday);
		
		holidayRepository.delete(holiday);
	}

	private void checkOwnerOfHolidayRequest(Holiday holiday) throws AccessDeniedException {
		HrUser hrUser = getCurrentUser();
		if(holiday.getClaimer().getId() != hrUser.getEmployee().getId())
			throw new AccessDeniedException("HolidayRequest is not owned by current user");
	}

	private HrUser getCurrentUser() {
		return (HrUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public Page<Holiday> findHolidayByExample(Holiday example, Pageable pageable) {

		Employee approver = example.getApprover();
		boolean approved = example.getApproved();
		Employee claimer = example.getClaimer();
		LocalDate claimDate = example.getClaimDate();
		LocalDate startDate = example.getStartDate();
		LocalDate endDate = example.getEndDate();

		Specification<Holiday> spec = Specification.where(null);

		if (approver != null && (approver.getName().equals("") || approver.getName() == null ))
			spec = spec.and(HolidaySpecifications.hasApproved(approved));

		if (approver != null && approver.getName() != null && !approver.getName().equals("")) {
			spec = spec.and(HolidaySpecifications.hasApprover(approver));
		}

		if (claimer != null && claimer.getName() != null && !claimer.getName().equals("")) {
			spec = spec.and(HolidaySpecifications.hasClaimer(claimer));
		}

		if (claimDate != null && startDate != null && endDate != null && startDate.isBefore(endDate))
			spec = spec.and(HolidaySpecifications.hasClaimDate(startDate, endDate));
//			spec = spec.and(HolidaySpecifications.hasClaimDate(claimDate, startDate, endDate));

		if (claimDate == null && startDate != null && endDate != null && startDate.isBefore(endDate)) {
			spec = spec.and(HolidaySpecifications.lessStartDate(endDate));
			spec = spec.and(HolidaySpecifications.greaterEndDate(startDate));
		}

		return holidayRepository.findAll(spec, pageable);
	}

}
