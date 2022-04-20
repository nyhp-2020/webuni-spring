package hu.webuni.hr.nyhp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.nyhp.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

	List<Position> findByName(String posname);
	
//	@Query("")
//	void setMinimumSalary(String posname,int minsal);

}
