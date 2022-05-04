package hu.webuni.hr.nyhp.mapper;

import org.mapstruct.Mapper;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.model.Holiday;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
	HolidayDto holidayToDto(Holiday holiday);
	Holiday dtoToHoliday(HolidayDto holidayDto);
}
