package hu.webuni.hr.nyhp.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.model.Holiday;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
	
	@Mapping(target = "claimer.pos", ignore = true)
	@Mapping(target = "claimer.company", ignore = true)
	@Mapping(target = "claimer.superior", ignore = true)
	@Mapping(target = "claimer.managedEmployees", ignore = true)
	@Mapping(target = "approver.pos", ignore = true)
	@Mapping(target = "approver.company", ignore = true)
	@Mapping(target = "approver.superior", ignore = true)
	@Mapping(target = "approver.managedEmployees", ignore = true)
	HolidayDto holidayToDto(Holiday holiday);
	
	@InheritInverseConfiguration
	Holiday dtoToHoliday(HolidayDto holidayDto);
	
	List<HolidayDto> holidaysToDtos(List<Holiday> holidays);
}
