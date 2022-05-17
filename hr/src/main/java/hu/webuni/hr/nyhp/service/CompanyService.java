package hu.webuni.hr.nyhp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Position;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;
import hu.webuni.hr.nyhp.repository.PositionRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PositionService positionService;
	
	@Transactional
	public Company modifyCompany(long id, Company company) {
		findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		company.setId(id);
		return save(company);
	}
	
	@Transactional
	public void deleteCompany(long id) {
		findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		delete(id);
	}
	
	@Transactional
	public Employee addNewEmployee(Employee employee, long coid) {
		findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return addEmployee(employee, coid);
	}
	
//	public void deleteEmployee(long coid, long emid) {
//		findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//		deleteEmployee(coid, emid);
//	}
	
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
		Company company = companyRepository.findById(id).get();
		for(Employee employee:company.getEmployees()) {
			employee.setCompany(null);
			employeeRepository.save(employee);
		}
		companyRepository.deleteById(id);
	}
	
	@Transactional
	public Employee addEmployee(Employee employee,long coid) {
		Company company = companyRepository.findById(coid).get();
		company.addEmployee(employee);
		positionService.setPositionForEmployee(employee);
		
//		Position transientPos = employee.getPos();
//		if(transientPos != null) {
//			List<Position> positionsByName = positionRepository.findByName(transientPos.getName());
//			if(positionsByName.isEmpty())
//				throw new RuntimeException("position with this name does not exist in DB");
//			Position positionInDB = positionsByName.get(0);
//			employee.setPos(positionInDB);
//		}
		
		return employeeRepository.save(employee);
		//return employee;
	}
	
	@Transactional
	public void deleteEmployee(long coid, long emid) {
		findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Company company = companyRepository.findById(coid).get();
		Employee employee = employeeRepository.findById(emid).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		
		employeeRepository.save(employee); //nem kéne mert a Transactional miatt már menedzselt entitás
		//companyRepository.findById(coid).get().delEmployee(emid);
	}
	
	@Transactional
	public List<Employee> changeEmployeeList(List<Employee> newemployees, long coid){
		findById(coid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Company company = companyRepository.findById(coid).get();
		for(Employee employee:company.getEmployees()) {
			employee.setCompany(null);
			employeeRepository.save(employee);
		}
		company.getEmployees().clear();
		//companyRepository.save(company);
		
		for(Employee employee:newemployees) {
			company.addEmployee(employee);
			positionService.setPositionForEmployee(employee);
			
//			Position transientPos = employee.getPos();
//			if(transientPos != null) {
//				List<Position> positionsByName = positionRepository.findByName(transientPos.getName());
//				if(positionsByName.isEmpty())
//					throw new RuntimeException("position with this name does not exist in DB");
//				Position positionInDB = positionsByName.get(0);
//				employee.setPos(positionInDB);
//			}
			
			Employee savedEmployee = employeeRepository.save(employee);
			employee.setId(savedEmployee.getId());
		}
		//companyRepository.save(company);
		
		//return company.getEmployees();
		//return companyRepository.findById(coid).get().getEmployees();
		
//		companyRepository.findById(coid).get().setEmployees(newemployees);
		return newemployees;
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
