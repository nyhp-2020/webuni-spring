package hu.webuni.transport.nyhp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.nyhp.model.Milestone;
import hu.webuni.transport.nyhp.model.TransportPlan;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

	@EntityGraph(attributePaths = { "address","plannedTime" })
	Optional<Milestone> findById(long id);
}