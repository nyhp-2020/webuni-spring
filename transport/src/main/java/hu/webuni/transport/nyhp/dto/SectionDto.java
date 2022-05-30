package hu.webuni.transport.nyhp.dto;

import javax.persistence.OneToOne;

import hu.webuni.transport.nyhp.model.Milestone;

public class SectionDto {
	private long id;
	private long fromMilestoneId;
	private long toMilestoneId;
	private long number;

	public SectionDto() {
	}

	public SectionDto(long id, long fromMilestoneId, long toMilestoneId, long number) {
		super();
		this.id = id;
		this.fromMilestoneId = fromMilestoneId;
		this.toMilestoneId = toMilestoneId;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromMilestoneId() {
		return fromMilestoneId;
	}

	public void setFromMilestoneId(long fromMilestoneId) {
		this.fromMilestoneId = fromMilestoneId;
	}

	public long getToMilestoneId() {
		return toMilestoneId;
	}

	public void setToMilestoneId(long toMilestoneId) {
		this.toMilestoneId = toMilestoneId;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	
}
