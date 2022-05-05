package hu.webuni.hr.nyhp.model;

import java.time.LocalDate;
import java.util.Objects;

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
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate claimDate;
	@ManyToOne
	private Employee approver = null;
	private Boolean approved = false;
	
	public Holiday() {}

	public Holiday(long id, Employee claimer, LocalDate startDate, LocalDate endDate, LocalDate claimDate,
			Employee approver, Boolean approved) {
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
		Holiday other = (Holiday) obj;
		return id == other.id;
	}
	
}
