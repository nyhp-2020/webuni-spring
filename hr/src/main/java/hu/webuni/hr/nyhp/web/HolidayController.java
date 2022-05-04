package hu.webuni.hr.nyhp.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.mapper.HolidayMapper;
import hu.webuni.hr.nyhp.model.Holiday;
import hu.webuni.hr.nyhp.repository.HolidayRepository;
import hu.webuni.hr.nyhp.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

//	@Autowired
//	HolidayRepository holidayRepository;

	@Autowired
	HolidayService holidayService;
	
	@Autowired
	HolidayMapper holidayMapper;

	@GetMapping("/{clid}/from/{start}/to/{end}")
	public HolidayDto createHoliday(@PathVariable long clid, @PathVariable LocalDate start,
			@PathVariable LocalDate end) {
		Holiday holiday = holidayService.createHoliday(clid, start, end);
		return holidayMapper.holidayToDto(holiday);
	}
}
