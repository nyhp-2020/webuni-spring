package hu.webuni.hr.nyhp.dto;

import java.time.LocalDate;

import hu.webuni.hr.nyhp.model.Employee;

public class HolidayDto {

	private long id;
	private Employee claimer;
	private LocalDate start;
	private LocalDate end;
	private LocalDate claimDate;
	private Employee approver;
	private Boolean approved = false;

	public HolidayDto() {
	}

	public HolidayDto(long id, Employee claimer, LocalDate start, LocalDate end, LocalDate claimDate, Employee approver,
			Boolean approved) {
		this.id = id;
		this.claimer = claimer;
		this.start = start;
		this.end = end;
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

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
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
