package hu.webuni.hr.nyhp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

	List<Position> findByName(String posname);
	
//	@EntityGraph(attributePaths = "employees")
//	Optional<Position> findById(long id);
	
//	@Query("")
//	void setMinimumSalary(String posname,int minsal);

}
