package hu.webuni.transport.nyhp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.nyhp.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>{

	@EntityGraph(attributePaths = { "sections" })
	Optional<TransportPlan> findById(long id);
}
