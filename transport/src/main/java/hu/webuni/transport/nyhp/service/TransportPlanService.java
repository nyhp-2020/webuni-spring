package hu.webuni.transport.nyhp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.nyhp.dto.DelayRequestDto;
import hu.webuni.transport.nyhp.model.Milestone;
import hu.webuni.transport.nyhp.model.Section;
import hu.webuni.transport.nyhp.model.TransportPlan;
import hu.webuni.transport.nyhp.repository.MilestoneRepository;
import hu.webuni.transport.nyhp.repository.TransportPlanRepository;

@Service
public class TransportPlanService {
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired IncomeService incomeService;
	
	@Transactional
	public TransportPlan save(TransportPlan transportPlan) {
		return transportPlanRepository.save(transportPlan);
	}
	
	public Optional<TransportPlan> findById(long id) {
		return transportPlanRepository.findById(id);
	}

	@Transactional
	public void setDelay(long id, DelayRequestDto delayRequestDto) {
		TransportPlan transportPlan = findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Milestone milestone = milestoneRepository.findById(delayRequestDto.getMilestoneId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		List<Section> sections = transportPlan.getSections();
		
		boolean milestoneInPath = false;
		Section foundSection = null;
		for(Section section : sections) {
			if(section.getFromMilestone().equals(milestone)) {
				milestoneInPath = true;
				foundSection = section;
				milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delayRequestDto.getDelayMinute()));
//				milestoneRepository.save(milestone); //Transaction do this
				Milestone toMilestone = section.getToMilestone();
				toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delayRequestDto.getDelayMinute()));
				break;
			}
			if(section.getToMilestone().equals(milestone)) {
				milestoneInPath = true;
				foundSection = section;
				milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delayRequestDto.getDelayMinute()));
				int index = sections.indexOf(section); //index of current section
				if(index < sections.size()-1) { //if exist next section
					Milestone fromMilestoneOfNextSection = sections.get(index+1).getFromMilestone();
					fromMilestoneOfNextSection.setPlannedTime(fromMilestoneOfNextSection.getPlannedTime().plusMinutes(delayRequestDto.getDelayMinute()));
				}
				break;
			}
		}
		
		if(!milestoneInPath)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		//transportPlan.setIncome(incomeService.getNewIncome(transportPlan.getIncome(), delayRequestDto.getDelayMinute()));
		transportPlanRepository.save(transportPlan);//also Transaction do this
		
	}

}
