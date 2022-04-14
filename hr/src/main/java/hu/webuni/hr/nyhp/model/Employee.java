package hu.webuni.hr.nyhp.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	public Employee() {
		
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
