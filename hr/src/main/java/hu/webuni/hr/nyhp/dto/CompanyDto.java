package hu.webuni.hr.nyhp.dto;

import java.util.ArrayList;
import java.util.List;

import hu.webuni.hr.nyhp.model.Employee;

public class CompanyDto {
	
	private long id;
	private String registry; 
	private String name;
	private String address;
	
	private List<EmployeeDto> Employees = new ArrayList<>();

	public CompanyDto(long id, String registry, String name, String address) { //, List<EmployeeDto> employees) {
		super();
		this.id = id;
		this.registry = registry;
		this.name = name;
		this.address = address;
		//Employees = employees;
	}
	
	public void addEmployeeDto(EmployeeDto employeeDto) {
		Employees.add(employeeDto);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployees() {
		return Employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		Employees = employees;
	}
	
}
