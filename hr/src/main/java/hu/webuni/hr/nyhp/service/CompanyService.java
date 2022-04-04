package hu.webuni.hr.nyhp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;

@Service
public class CompanyService {
	
	private Map<Long, Company> companies = new HashMap<>();
	
	public List<Company> findAll(){
		return new ArrayList<>(companies.values());
	}
	
	public Company findById(long id){
		return companies.get(id);
	}
	
	public Company save(Company company) {
		companies.put(company.getId(), company);
		return company;
	}
	
	public void delete(long id) {
		companies.remove(id);
	}
	
	public Employee addEmployee(Employee employee,long coid) {
		companies.get(coid).addEmployee(employee);
		return employee;
	}
	
	public void deleteEmployee(long coid, long emid) {
		companies.get(coid).delEmployee(emid);
	}
	
	public List<Employee> changeEmployeeList(List<Employee> newemployees, long coid){
		companies.get(coid).setEmployees(newemployees);
		return newemployees;
	}

}
