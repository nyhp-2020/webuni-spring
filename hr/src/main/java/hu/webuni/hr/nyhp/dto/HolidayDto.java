package hu.webuni.hr.nyhp.dto;

import java.time.LocalDate;

import hu.webuni.hr.nyhp.model.Employee;

public class HolidayDto {

	private long id;
	private Employee claimer;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate claimDate;
	private Employee approver;
	private Boolean approved;

	public HolidayDto() {
	}

	public HolidayDto(long id, Employee claimer, LocalDate startDate, LocalDate endDate, LocalDate claimDate,
			Employee approver, Boolean approved) {
		super();
		this.id = id;
		this.claimer = claimer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.claimDate = claimDate;
		this.approver = approver;
		this.approved = approved;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getClaimer() {
		return claimer;
	}

	public void setClaimer(Employee claimer) {
		this.claimer = claimer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

}
