package hu.webuni.hr.nyhp.model;

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
}
