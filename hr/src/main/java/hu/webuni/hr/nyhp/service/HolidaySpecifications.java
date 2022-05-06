package hu.webuni.hr.nyhp.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Employee_;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.model.Holiday_;

public class HolidaySpecifications {
	
//	public static Specification<Employee> hasApproved(String approved) {
//		return (root, cq, cb) -> cb.isTrue(root.get(Holiday_.approved));
//	}

	public static Specification<Holiday> hasClaimer(Employee claimer) {
		return (root, cq, cb) -> cb.like((root.get(Holiday_.claimer).get(Employee_.name)), claimer.getName().toUpperCase() + "%");
		//return (root, cq, cb) -> cb.like(cb.upper(root.get(Employee_.name)), claimer.getName().toUpperCase() + "%");
		//cb.equal(root.get(Employee_.pos).get(Position_.name), pos.getName());
	}
}
