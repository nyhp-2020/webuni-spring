package hu.webuni.hr.nyhp.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.nyhp.dto.HolidayDto;
import hu.webuni.hr.nyhp.mapper.HolidayMapper;
import hu.webuni.hr.nyhp.model.Holiday;
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
	@PreAuthorize("#clid == authentication.principal.employee.id")
	public HolidayDto createHoliday(@PathVariable long clid, @PathVariable LocalDate start,
			@PathVariable LocalDate end) {
		Holiday holiday = holidayService.createHoliday(clid, start, end);
		return holidayMapper.holidayToDto(holiday);
	}

	@GetMapping
	public List<HolidayDto> findAll(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<Holiday> holidays = holidayService.findAll(pageNo, pageSize, sortBy);
		return holidayMapper.holidaysToDtos(holidays);
	}

	@GetMapping("/judge/{hid}/{aid}")
	//@PreAuthorize("#aid == authentication.principal.employee.id")
	public HolidayDto judgeRequest(@PathVariable long hid, @PathVariable long aid,
			@RequestParam(defaultValue = "false") boolean approved) {
		Holiday holiday = holidayService.judgeRequest(hid, aid, approved);
		return holidayMapper.holidayToDto(holiday);
	}

	@GetMapping("/modify/{hid}/{clid}/from/{start}/to/{end}")
	public HolidayDto modifyRequest(@PathVariable long hid, @PathVariable long clid, @PathVariable LocalDate start,
			@PathVariable LocalDate end) {
		Holiday holiday = holidayService.modifyRequest(hid, clid, start, end);
		return holidayMapper.holidayToDto(holiday);
	}
	
	@GetMapping("/delete/{hid}/{clid}")
	public void deleteRequest(@PathVariable long hid, @PathVariable long clid) {
		holidayService.deleteRequest(hid, clid);
	}
	
	@PostMapping("/example")
	public List<HolidayDto> findHolidayByExample(@RequestBody HolidayDto holidayDto, Pageable pageable) {
		Holiday holiday = holidayMapper.dtoToHoliday(holidayDto);
		Page<Holiday> page = holidayService.findHolidayByExample(holiday,pageable);
		return holidayMapper.holidaysToDtos(page.getContent());
	}
}
