package hu.webuni.transport.nyhp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Section {
	
	@Id
	@GeneratedValue
	private long id;
	private long number;
	

}
