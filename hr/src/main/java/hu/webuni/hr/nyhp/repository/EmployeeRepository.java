package hu.webuni.hr.nyhp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.nyhp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findBySalaryGreaterThan(int salary);

	List<Employee> findByPositionLike(String position);

	List<Employee> findByNameStartingWithIgnoreCase(String string);

	List<Employee> findByStartdBetween(LocalDateTime d1, LocalDateTime d2);

	@Query("SELECT e.position, AVG(e.salary) AS avgsalary FROM Employee e WHERE e.company.id = :id GROUP BY e.position ORDER BY avgsalary DESC")
	List<Object> getAvgSalaryOfCompanyGroupedByPosition(long id);
}
