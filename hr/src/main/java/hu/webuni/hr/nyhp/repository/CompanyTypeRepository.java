package hu.webuni.hr.nyhp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.nyhp.model.CompanyType;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
	CompanyType findByName(String typename);
}
