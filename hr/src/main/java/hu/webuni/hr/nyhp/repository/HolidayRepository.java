package hu.webuni.hr.nyhp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.nyhp.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long>, JpaSpecificationExecutor<Holiday> {
	Page<Holiday> findAll(Pageable pageable);
}
