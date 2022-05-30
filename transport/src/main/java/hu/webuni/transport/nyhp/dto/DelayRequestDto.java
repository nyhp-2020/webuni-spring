package hu.webuni.transport.nyhp.dto;

public class DelayRequestDto {
	long id;
	long milestoneId;
	long delayMinute;
	

	public DelayRequestDto(long id, long milestoneId, long delayMinute) {
		super();
		this.id = id;
		this.milestoneId = milestoneId;
		this.delayMinute = delayMinute;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	public long getDelayMinute() {
		return delayMinute;
	}
	public void setDelayMinute(long delayMinute) {
		this.delayMinute = delayMinute;
	}

}
