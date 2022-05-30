package hu.webuni.transport.nyhp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.transport.nyhp.dto.AddressDto;
import hu.webuni.transport.nyhp.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressDto addressToDto(Address address);
	Address dtoToAddress(AddressDto addressDto);
	List<AddressDto> addressesToDtos(List<Address> addresses);
}
