package hu.webuni.transport.nyhp.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Address address;
	private LocalDateTime plannedTime;
	
	public Milestone() {}
	
	public Milestone(long id, Address address, LocalDateTime plannedTime) {
		super();
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
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
		if (! (obj instanceof Milestone))
			return false;
		Milestone other = (Milestone) obj;
		return getId() == other.getId();
	}
	
	
	
}
