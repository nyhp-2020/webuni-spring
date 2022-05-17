package hu.webuni.hr.nyhp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Position;
import hu.webuni.hr.nyhp.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	PositionRepository positionRepository;
	
	public void setPositionForEmployee(Employee employee) {
		Position position = employee.getPos();
		if(position != null) {
			String positionName = position.getName();
			if(positionName != null) {
				List<Position> positionsByName = positionRepository.findByName(positionName);
				if(positionsByName.isEmpty()) {
					position = positionRepository.save(new Position(positionName));
				}else {
					position = positionsByName.get(0);
				}
			}
		}
		employee.setPos(position);
	}

}
