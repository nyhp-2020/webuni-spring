package hu.webuni.hr.nyhp.mapper;

import org.mapstruct.Mapper;

import hu.webuni.hr.nyhp.dto.PositionDto;
import hu.webuni.hr.nyhp.model.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {
	
	PositionDto positionToDto(Position position);
	Position dtoToPosition(PositionDto positionDto);

}
