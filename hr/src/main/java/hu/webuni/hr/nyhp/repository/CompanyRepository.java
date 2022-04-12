package hu.webuni.hr.nyhp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.nyhp.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
