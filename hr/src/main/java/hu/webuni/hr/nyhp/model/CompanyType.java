package hu.webuni.hr.nyhp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CompanyType {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy = "type")
	Set<Company> companies;

	public CompanyType() {
	}

	public CompanyType(long id, String name, Set<Company> companies) {
		super();
		this.id = id;
		this.name = name;
		this.companies = companies;
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

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public void addCompany(Company company) {
		company.setType(this);
		if (this.companies == null)
			this.companies = new HashSet<>();
		this.companies.add(company);
	}

}
