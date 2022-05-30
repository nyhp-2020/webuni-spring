package hu.webuni.transport.nyhp.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.transport.nyhp.model.Address;
import hu.webuni.transport.nyhp.model.Address_;

public class AddressSpecifications {
	
	public static Specification<Address> hasIsoCode(String isoCode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.isoCode), isoCode);
	}

	public static Specification<Address> hasCity(String city) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.city)), city.toUpperCase() + "%");
	}

	public static Specification<Address> hasZipcode(String zipcode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.zipcode), zipcode);
	}

	public static Specification<Address> hasStreet(String street) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.street)), street.toUpperCase() + "%");
	}

}
