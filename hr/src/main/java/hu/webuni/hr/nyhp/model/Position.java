package hu.webuni.hr.nyhp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Position {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String deegree;
	private int minsalary;
	@OneToMany(mappedBy = "pos")
	Set<Employee> employees;
	
	public Position() {
		
	}
	
	public Position(String name) {
		this.name = name;	
	}
	
	public Position(long id, String name, String deegree, int minsalary, Set<Employee> employees) {
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
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		employee.setPos(this);
		if (this.employees == null)
			this.employees = new HashSet<>();
		this.employees.add(employee);
	}

}
