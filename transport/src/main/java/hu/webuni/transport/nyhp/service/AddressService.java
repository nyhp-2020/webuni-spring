package hu.webuni.transport.nyhp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.nyhp.model.Address;
import hu.webuni.transport.nyhp.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public List<Address> getAll(){
		return addressRepository.findAll();
	}
	
	public Optional<Address> findById(long id) {
		return addressRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id) {
		findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
		addressRepository.deleteById(id);
	}
	
	@Transactional
	public Address modifyAddress(long id, Address address) {
		findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(address == null || address.getId() != id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		return addressRepository.save(address);
	}
	
//	public List<Holiday> findAll(int pageNo, int pageSize, String sortBy) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//		Page<Holiday> page = holidayRepository.findAll(pageable);
//		List<Holiday> holidays = page.getContent();
//		return holidays;
//	}
	
	public List<Address> findAddressesByExample(Address example,Pageable pageable) {
		if(example == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		String isoCode = example.getIsoCode();
		String city = example.getCity();
		String zipcode = example.getZipcode();
		String street = example.getStreet();
		
		Specification<Address> spec = Specification.where(null);
		
		if (StringUtils.hasText(isoCode)) {
			spec = spec.and(AddressSpecifications.hasIsoCode(isoCode));
		}
		
		if (StringUtils.hasText(city)) {
			spec = spec.and(AddressSpecifications.hasCity(city));
		}
		
		if (StringUtils.hasText(zipcode)) {
			spec = spec.and(AddressSpecifications.hasZipcode(zipcode));
		}
		
		if (StringUtils.hasText(street)) {
			spec = spec.and(AddressSpecifications.hasStreet(street));
		}
		
		//return addressRepository.findAll(spec, Sort.by("id"));
		
		Page<Address> page = addressRepository.findAll(spec, pageable);
		return page.getContent();
	}

}
