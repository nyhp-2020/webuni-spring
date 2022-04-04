package hu.webuni.hr.nyhp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hu.webuni.hr.nyhp.model.Employee;

public abstract class EmployeeService {
	
	private Map<Long, Employee> employees = new HashMap<>();
	
	public List<Employee> findAll(){
		return new ArrayList<>(employees.values());
	}
	
	public Employee findById(long id){
		return employees.get(id);
	}
	
	public Employee save(Employee employee) {
		//checkUniqueIata(airport.getIata());
		employees.put(employee.getId(), employee);
		return employee;
	}
		
//	public Employee modify(Employee employee) {
//		employees.put(employee.getId(), employee);
//		return employee;
//	}
	
	public void delete(long id) {
		employees.remove(id);
	}
	
	public List<Employee> findOfHigherSalary(int salary){
		return (new ArrayList<>(employees.values())).stream().filter(e -> e.getSalary() > salary)
				.collect(Collectors.toList());
	}
	
	public abstract int getPayRaisePercent(Employee employee);

}
