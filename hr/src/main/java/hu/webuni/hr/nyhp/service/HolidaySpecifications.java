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
		//return (root, cq, cb) -> cb.like(cb.upper(root.get(Employee_.name)), claimer.getName().toUpperCase() + "%");
		//cb.equal(root.get(Employee_.pos).get(Position_.name), pos.getName());
	}
	
	public static Specification<Holiday> hasClaimDate(LocalDate claimDate, LocalDate startDate, LocalDate endDate) {
		return (root, cq, cb) -> cb.between(root.get(Holiday_.claimDate), startDate, endDate);
	}
	
	public static Specification<Holiday> hasStartDate(LocalDate startDate, LocalDate endDate) {
		return (root, cq, cb) -> cb.between(root.get(Holiday_.startDate), startDate, endDate);
	}
	
	public static Specification<Holiday> hasEndDate(LocalDate startDate, LocalDate endDate) {
		return (root, cq, cb) -> cb.between(root.get(Holiday_.endDate), startDate, endDate);
	}
	
	public static Specification<Holiday> lessStartDate(LocalDate startDate) {
		return (root, cq, cb) -> cb.lessThan(root.get(Holiday_.startDate), startDate);
	}
	
	public static Specification<Holiday> greaterEndDate(LocalDate endDate) {
		return (root, cq, cb) -> cb.greaterThan(root.get(Holiday_.startDate), endDate);
	}
}
