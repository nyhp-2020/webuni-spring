package hu.webuni.hr.nyhp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query("Select c FROM Company c join c.employees e where e.salary > :salary")
	List<Company> getCompaniesHaveEmployeeWithHigherSalary(int salary);
	
	//@Query("Select co FROM (Select c AS co, Count(e) AS cnt FROM Company c join c.employees e Group By c) WHERE cnt > :count")
	@Query("Select c FROM Company c join c.employees e Group By c HAVING COUNT(e) > :count") 
	List<Company> getCompaniesCountOfEmployeesHigher(long count);

	//spring.jpa.open-in-view=false
	@Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.employees")
	public List<Company> findAllWithEmployees();

}
