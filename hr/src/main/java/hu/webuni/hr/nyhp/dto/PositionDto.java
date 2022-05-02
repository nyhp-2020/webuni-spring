package hu.webuni.hr.nyhp.dto;

import java.util.HashSet;
import java.util.Set;

public class PositionDto {
	private long id;
	private String name;
	private String deegree;
	private int minsalary;
	Set<EmployeeDto> employees = new HashSet<>();
	
	public PositionDto() {}
	
	public PositionDto(long id, String name, String deegree, int minsalary, Set<EmployeeDto> employees) {
		super();
		this.id = id;
		this.name = name;
		this.deegree = deegree;
		this.minsalary = minsalary;
		this.employees = employees;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeegree() {
		return deegree;
	}
	public void setDeegree(String deegree) {
		this.deegree = deegree;
	}
	public int getMinsalary() {
		return minsalary;
	}
	public void setMinsalary(int minsalary) {
		this.minsalary = minsalary;
	}
	public Set<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<EmployeeDto> employees) {
		this.employees = employees;
	};
}
