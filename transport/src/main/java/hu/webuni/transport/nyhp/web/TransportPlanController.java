package hu.webuni.transport.nyhp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.transport.nyhp.dto.DelayRequestDto;
import hu.webuni.transport.nyhp.dto.TransportPlanDto;
import hu.webuni.transport.nyhp.mapper.TransportPlanMapper;
import hu.webuni.transport.nyhp.model.TransportPlan;
import hu.webuni.transport.nyhp.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {
	
@Autowired
TransportPlanService transportPlanService;

@Autowired
TransportPlanMapper transportPlanMapper;
	
	
	@PutMapping("/{id}/delay")
	public void setDelay(@PathVariable long id,@RequestBody DelayRequestDto delayRequestDto) {
		transportPlanService.setDelay(id,delayRequestDto);
//		return transportPlanMapper.transportplanToDto(transportPlan);
	}

}

