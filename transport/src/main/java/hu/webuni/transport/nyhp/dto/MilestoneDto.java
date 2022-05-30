package hu.webuni.transport.nyhp.dto;

import java.time.LocalDateTime;

public class MilestoneDto {
	private long id;
	private long addressId;
	private LocalDateTime plannedTime;
	
	public MilestoneDto() {}

	public MilestoneDto(long id, long addressId, LocalDateTime plannedTime) {
		super();
		this.id = id;
		this.addressId = addressId;
		this.plannedTime = plannedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	
	
}
