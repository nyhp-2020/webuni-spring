package hu.webuni.transport.nyhp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.nyhp.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}