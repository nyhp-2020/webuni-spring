package hu.webuni.hr.nyhp.dto;

import java.util.ArrayList;
import java.util.List;

import hu.webuni.hr.nyhp.model.Employee;

public class CompanyDto {

	private long id;
	private String registry;
	private String name;
	private String address;

	private List<EmployeeDto> employees = new ArrayList<>();
	
	public CompanyDto() {
		
	}

	// special copy constructor
	public CompanyDto(CompanyDto companyDto) {
		this.id = companyDto.id;
		this.registry = companyDto.registry;
		this.name = companyDto.name;
		this.address = companyDto.address;
		this.employees = null;
	}

	public CompanyDto(long id, String registry, String name, String address ,List<EmployeeDto> employees) {
		super();
		this.id = id;
		this.registry = registry;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public void addEmployeeDto(EmployeeDto employeeDto) {
		employees.add(employeeDto);
	}

	public void delEmployeeDto(long id) {
//		EmployeeDto em = employees.stream().filter(e -> e.getId() == id).findFirst().get();
//		int index = employees.indexOf(em);
//		employees.remove(index);
		employees.removeIf(e -> e.getId() == id);
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
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

}
