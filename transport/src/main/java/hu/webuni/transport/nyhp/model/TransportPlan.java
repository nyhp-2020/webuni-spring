package hu.webuni.transport.nyhp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class TransportPlan {
	
	@Id
	@GeneratedValue
	private long id;
	private long income;
	@OneToMany
	@OrderBy("number")
	private List<Section> sections = new ArrayList<>();
	
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransportPlan other = (TransportPlan) obj;
		return id == other.id;
	}

	
}
