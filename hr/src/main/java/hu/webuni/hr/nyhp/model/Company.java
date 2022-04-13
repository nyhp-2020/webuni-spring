package hu.webuni.hr.nyhp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private long id;
	private String registry;
	private String name;
	private String address;
	// @OneToMany(mappedBy="company", cascade = {CascadeType.PERSIST})
	@OneToMany(mappedBy = "company")
	@OrderBy("id")
	private List<Employee> employees = new ArrayList<>();

	public Company() {
	}

	public Company(long id, String registry, String name, String address, List<Employee> employees) {
		super();
		this.id = id;
		this.registry = registry;
		this.name = name;
		this.address = address;
		this.employees = employees;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		employee.setCompany(this);
		if (this.employees == null)
			this.employees = new ArrayList<>();
		this.employees.add(employee);
	}

	//Deprecated
//	public void delEmployee(long emid) {
//		employees.removeIf(e -> e.getId() == emid);
//	}
}
