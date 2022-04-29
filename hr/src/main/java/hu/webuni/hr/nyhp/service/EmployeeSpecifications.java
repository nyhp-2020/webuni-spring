package hu.webuni.hr.nyhp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Employee_;
import hu.webuni.hr.nyhp.model.Position;

public class EmployeeSpecifications {

	public static Specification<Employee> hasId(long id) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}

	public static Specification<Employee> hasName(String name) {
		return (root, cq, cb) -> cb.like(root.get(Employee_.name), name + "%");
	}

	public static Specification<Employee> hasPos(Position pos) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.pos.getName()), pos.getName());
	}

	public static Specification<Employee> hasSalary(int salary) {
		return (root, cq, cb) -> cb.between(root.get(Employee_.salary), (int)(salary*(1 - 0.05)), (int)(salary*(1 + 0.05)));
	}

//	public static Specification<Employee> hasStartd(LocalDateTime startd) {
//		return (root, cq, cb) -> cb.equal(root.get(Employee_.startd),startd);
//	}
	
	public static Specification<Employee> hasStartd(LocalDateTime startd) {
		LocalDateTime startOfDay = LocalDateTime.of(startd.toLocalDate(), LocalTime.of(0, 0));
		return (root, cq, cb) -> cb.between(root.get(Employee_.startd), startOfDay, startOfDay.plusDays(1));
	}
	
	public static Specification<Employee> hasCompany(Company company) {
		return (root, cq, cb) -> cb.like(root.get(Employee_.company.getName()), company.getName() + "%");
	}

}
