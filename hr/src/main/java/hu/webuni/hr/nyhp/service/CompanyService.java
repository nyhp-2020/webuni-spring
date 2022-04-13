package hu.webuni.hr.nyhp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id){
		return companyRepository.findById(id);
	}
	
	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	@Transactional
	public void delete(long id) {
		companyRepository.deleteById(id);
	}
	
	public Employee addEmployee(Employee employee,long coid) {
		Company company = companyRepository.findById(coid).get();
		company.addEmployee(employee);
		return employeeRepository.save(employee);
		//return employee;
	}
	
	public void deleteEmployee(long coid, long emid) {
		Company company = companyRepository.findById(coid).get();
		Employee employee = employeeRepository.findById(emid).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		//companyRepository.findById(coid).get().delEmployee(emid);
	}
	
	public List<Employee> changeEmployeeList(List<Employee> newemployees, long coid){
		Company company = companyRepository.findById(coid).get();
		for(Employee employee:company.getEmployees()) {
			employee.setCompany(null);
		}
		company.getEmployees().clear();
		
		for(Employee employee:newemployees) {
			company.addEmployee(employee);
			employeeRepository.save(employee);
		}
		
		return company.getEmployees();
		
//		companyRepository.findById(coid).get().setEmployees(newemployees);
//		return newemployees;
	}
	
//	private Map<Long, Company> companies = new HashMap<>();
	
//	public List<Company> findAll(){
//		return new ArrayList<>(companies.values());
//	}
	
//	public Company findById(long id){
//		return companies.get(id);
//	}
	
//	public Company save(Company company) {
//		companies.put(company.getId(), company);
//		return company;
//	}
	
//	public void delete(long id) {
//		companies.remove(id);
//	}
	
//	public Employee addEmployee(Employee employee,long coid) {
//		companies.get(coid).addEmployee(employee);
//		return employee;
//	}
	
//	public void deleteEmployee(long coid, long emid) {
//		companies.get(coid).delEmployee(emid);
//	}
	
//	public List<Employee> changeEmployeeList(List<Employee> newemployees, long coid){
//		companies.get(coid).setEmployees(newemployees);
//		return newemployees;
//	}

}
