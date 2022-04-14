package hu.webuni.hr.nyhp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.nyhp.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	
//	@Query("")
//	void setMinimumSalary(String posname,int minsal);

}
