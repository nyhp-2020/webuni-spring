package hu.webuni.hr.nyhp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Holiday {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Employee claimer;
	private LocalDate start;
	private LocalDate end;
	private LocalDate claimDate;
	@ManyToOne
	private Employee approver = null;
	private Boolean approved = false;
	
	public Holiday() {}
	
	public Holiday(long id, Employee claimer, LocalDate start, LocalDate end, LocalDate claimDate, Employee approver,
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
