package hu.webuni.transport.nyhp.model;

import javax.persistence.GeneratedValue;

import nonapi.io.github.classgraph.json.Id;

public class Address {
	@Id
	@GeneratedValue
	private long id;
}
