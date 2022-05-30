package hu.webuni.transport.nyhp.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.transport.nyhp.dto.SectionDto;
import hu.webuni.transport.nyhp.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {
	
	@Mapping(source = "fromMilestone.id", target = "fromMilestoneId")
	@Mapping(source = "toMilestone.id", target = "toMilestoneId")
	SectionDto sectionToDto(Section section);
	
	@InheritInverseConfiguration
	Section dtoToSection(SectionDto sectionDto);
	
	List<SectionDto> sectionsToDtos(List<Section> sections);
	List<Section> sectionDtosToSections(List<SectionDto> sectionDtos);
}
