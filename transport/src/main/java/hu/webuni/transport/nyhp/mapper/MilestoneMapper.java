package hu.webuni.transport.nyhp.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.transport.nyhp.dto.MilestoneDto;
import hu.webuni.transport.nyhp.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {
	
	@Mapping(source = "address.id", target = "addressId")
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@InheritInverseConfiguration
	Milestone dtoToMilestone(MilestoneDto milestoneDto);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
}
