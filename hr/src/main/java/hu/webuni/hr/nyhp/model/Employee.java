package hu.webuni.hr.nyhp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String position;
	@ManyToOne
	private Position pos;
	private int salary;
	private LocalDateTime startd;
	@ManyToOne
	private Company company;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@ManyToOne
	private Employee superior;
	
	@OneToMany(mappedBy = "superior")
	private List<Employee> managedEmployees;
	
	@ElementCollection(fetch = FetchType.EAGER) //Role-ok behúzása
	private Set<String> roles;

	public Employee() {

	}

	public Employee(long id, String name, String position, Position pos, int salary, LocalDateTime startd,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.pos = pos;
		this.salary = salary;
		this.startd = startd;
		this.company = company;
	}

	public Employee(long id, String name, String position, int salary, LocalDateTime startd) {
		// super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startd = startd;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
		if (pos != null)
			this.position = pos.getName();
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartd() {
		return startd;
	}

	public void setStartd(LocalDateTime startd) {
		this.startd = startd;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
	}
	

	public void addManagedEmployee(Employee emp) {
		emp.setSuperior(this);
		if(this.managedEmployees == null) {
			this.managedEmployees = new ArrayList<>();
		}
		this.managedEmployees.add(emp);
	}
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	

	public List<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(List<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id;
	}

}
