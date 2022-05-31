package hu.webuni.transport.nyhp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.nyhp.dto.AddressDto;
import hu.webuni.transport.nyhp.mapper.AddressMapper;
import hu.webuni.transport.nyhp.model.Address;
import hu.webuni.transport.nyhp.repository.AddressRepository;
import hu.webuni.transport.nyhp.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	AddressService addressService;

//	@PostMapping
//	public long createAddress(@RequestBody AddressDto addressDto) {
//		Address address = addressService.save(addressMapper.dtoToAddress(addressDto));
//		return address.getId();
//	}

	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		Address address = addressService.save(addressMapper.dtoToAddress(addressDto));
		return addressMapper.addressToDto(address);
	}

	@GetMapping
	public List<AddressDto> getAll() {
		return addressMapper.addressesToDtos(addressService.getAll());
	}

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable long id) {
		Address address = addressService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return addressMapper.addressToDto(address);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.delete(id);
	}

	@PutMapping("/{id}")
	public AddressDto modifyAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto) {
		return addressMapper.addressToDto(addressService.modifyAddress(id, addressMapper.dtoToAddress(addressDto)));
	}

	@PostMapping("/search")
	public List<AddressDto> findAddressesByExample(@RequestBody AddressDto addressDto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(required = false) Integer size,
			@RequestParam(defaultValue = "id,asc") String sort) {
		Address address = addressMapper.dtoToAddress(addressDto);

		if (size == null)
			size = (int) addressRepository.count();

		String[] sortlist = sort.split(",");
		String order;
		if (sortlist.length < 1)
			order = null;
		else
			order = sortlist[0];
		if (order == null || order.length() == 0)
			order = "id";
		String direction;
		if (sortlist.length <= 1)
			direction = null;
		else
			direction = sortlist[1];
		Pageable pageable;
		if (direction == null || direction.length() == 0 || direction.equals("asc")) {
			pageable = PageRequest.of(page, size, Sort.by(order).ascending());
		} else {
			pageable = PageRequest.of(page, size, Sort.by(order).descending());
		}

//		Pageable pageable = PageRequest.of(page, size, Sort.by(order));
		return addressMapper.addressesToDtos(addressService.findAddressesByExample(address, pageable));
	}

}
