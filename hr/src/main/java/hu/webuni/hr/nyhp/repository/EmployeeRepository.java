package hu.webuni.hr.nyhp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.nyhp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findBySalaryGreaterThan(int salary);
	
	List<Employee> findByPositionLike(String position);
	List<Employee> findByNameStartingWithIgnoreCase(String string);
	List<Employee> findByStartdBetween(LocalDateTime d1,LocalDateTime d2);
}
