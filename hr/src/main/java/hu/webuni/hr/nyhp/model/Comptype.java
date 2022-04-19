package hu.webuni.hr.nyhp.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Comptype {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy = "cotp")
	Set<Company> companies;
}
