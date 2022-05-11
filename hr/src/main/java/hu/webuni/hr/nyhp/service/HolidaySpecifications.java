package hu.webuni.hr.nyhp.service;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Employee_;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.model.Holiday_;

public class HolidaySpecifications {
	
	public static Specification<Holiday> hasApproved(boolean approved) {
		return (root, cq, cb) -> cb.equal(root.get(Holiday_.approved), approved);
	}

	public static Specification<Holiday> hasClaimer(Employee claimer) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Holiday_.claimer).get(Employee_.name)), claimer.getName().toUpperCase() + "%");
	}
	
	public static Specification<Holiday> hasApprover(Employee approver) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Holiday_.approver).get(Employee_.name)), approver.getName().toUpperCase() + "%");
	}
	
	public static Specification<Holiday> hasClaimDate(/*LocalDate claimDate,*/ LocalDate startDate, LocalDate endDate) {
		return (root, cq, cb) -> cb.between(root.get(Holiday_.claimDate), startDate, endDate);
	}
	
//	public static Specification<Holiday> isStartDateBetween(LocalDate startDate, LocalDate endDate) {
//		return (root, cq, cb) -> cb.between(root.get(Holiday_.startDate), startDate, endDate);
//	}
//	
//	public static Specification<Holiday> isEndDateBetween(LocalDate startDate, LocalDate endDate) {
//		return (root, cq, cb) -> cb.between(root.get(Holiday_.endDate), startDate, endDate);
//	}
	
	public static Specification<Holiday> lessStartDate(LocalDate date) {
		return (root, cq, cb) -> cb.lessThan(root.get(Holiday_.startDate), date);
	}
	
	public static Specification<Holiday> greaterEndDate(LocalDate date) {
		return (root, cq, cb) -> cb.greaterThan(root.get(Holiday_.endDate), date);
	}
}
