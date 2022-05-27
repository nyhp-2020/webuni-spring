package hu.webuni.transport.nyhp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.nyhp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
