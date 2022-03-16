package hu.webuni.hr.nyhp.model;

import java.time.LocalDateTime;

public class Employee {
	private long id;
	private String name;
	private String post;
	private int salary;
	private LocalDateTime startd;

	public Employee(long id, String name, String post, int salary, LocalDateTime startd) {
		//super();
		this.id = id;
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.startd = startd;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
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
