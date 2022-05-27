package hu.webuni.transport.nyhp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransportPlan {
	
	@Id
	@GeneratedValue
	private long id;
	private long income;
	private List<Section> sections;
	
	public TransportPlan() {}
	
	public TransportPlan(long id, long income, List<Section> sections) {
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	
}
