package hu.webuni.transport.nyhp.dto;

import java.util.ArrayList;
import java.util.List;

public class TransportPlanDto {
	private long id;
	private long income;
	private List<SectionDto> sections = new ArrayList<>();

	public TransportPlanDto() {
	}

	public TransportPlanDto(long id, long income, List<SectionDto> sections) {
		super();
		this.id = id;
		this.income = income;
		this.sections = sections;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
	}
	
	
}
