package hu.webuni.hr.nyhp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.nyhp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//	List<Employee> findBySalaryGreaterThan(int salary);
	
	Page<Employee> findBySalaryGreaterThan(int salary, Pageable pageable);

	List<Employee> findByPositionLike(String position);

	List<Employee> findByNameStartingWithIgnoreCase(String string);

	List<Employee> findByStartdBetween(LocalDateTime d1, LocalDateTime d2);
	
	List<Employee> findByCompanyIsNull();
	
///	@Query("SELECT e.position.name AS position, avg(e.salary) AS averageSalary "
//			+ "FROM Company c "
//			+ "INNER JOIN c.employees e "
//			+ "WHERE c.id=:companyId "
//			+ "GROUP BY e.position.name "
//			+ "ORDER BY avg(e.salary) DESC")
	
	@Query("SELECT e.position, AVG(e.salary) AS avgsalary FROM Employee e WHERE e.company.id = :id GROUP BY e.position ORDER BY avgsalary DESC")
	List<Object[]> getAvgSalaryOfCompanyGroupedByPosition(long id);
}
