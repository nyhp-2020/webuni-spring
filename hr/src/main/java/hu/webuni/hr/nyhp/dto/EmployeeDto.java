package hu.webuni.hr.nyhp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Position;

public class EmployeeDto {

	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String position;
	
	//private PositionDto pos;
	
	@Min(1)
	private int salary;
	@Past
	private LocalDateTime startd;
	
	//private CompanyDto company;
	private String companyName;

	public EmployeeDto() {
	}

	public EmployeeDto(long id, String name, String position, int salary, LocalDateTime startd) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startd = startd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartd() {
		return startd;
	}

	public void setStartd(LocalDateTime startd) {
		this.startd = startd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

//	public PositionDto getPos() {
//		return pos;
//	}
//
//	public void setPos(PositionDto pos) {
//		this.pos = pos;
//	}
//
//	public CompanyDto getCompany() {
//		return company;
//	}
//
//	public void setCompany(CompanyDto company) {
//		this.company = company;
//	}

}
