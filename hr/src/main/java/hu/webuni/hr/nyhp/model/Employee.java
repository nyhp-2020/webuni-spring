package hu.webuni.hr.nyhp.model;

import java.time.LocalDateTime;

public class Employee {
	private long id;
	private String name;
	private String position;
	private int salary;
	private LocalDateTime startd;
	
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

	
}
