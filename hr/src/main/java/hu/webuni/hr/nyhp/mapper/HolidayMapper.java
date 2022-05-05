package hu.webuni.hr.nyhp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.model.Holiday;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
	
	@Mapping(target = "claimer.pos", ignore = true)
	@Mapping(target = "claimer.company", ignore = true)
	@Mapping(target = "approver.pos", ignore = true)
	@Mapping(target = "approver.company", ignore = true)
	HolidayDto holidayToDto(Holiday holiday);
	
	@InheritInverseConfiguration
	Holiday dtoToHoliday(HolidayDto holidayDto);
}
