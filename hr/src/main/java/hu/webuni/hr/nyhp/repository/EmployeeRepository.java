package hu.webuni.hr.nyhp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
//	List<Employee> findBySalaryGreaterThan(int salary);

//	@EntityGraph(attributePaths = { "pos", "company" })
//	Optional<Employee> findById(long id);

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

	//@EntityGraph(attributePaths = { "pos", "company" })
	@EntityGraph(attributePaths = { "managedEmployees","superior","roles" })
	Optional<Employee> findByUsername(String username);
}
