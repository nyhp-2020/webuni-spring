package hu.webuni.transport.nyhp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.transport.nyhp.dto.TransportPlanDto;
import hu.webuni.transport.nyhp.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {
	TransportPlanDto transportplanToDto(TransportPlan transportPlan);
	TransportPlan dtoToTransportPlan(TransportPlanDto tranportPlanDto);
	
	List<TransportPlanDto> transportPlansToDtos(List<TransportPlan> transportPlans);
}
