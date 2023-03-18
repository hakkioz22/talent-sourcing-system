package com.tpe.repository;

import com.tpe.domain.Candidate;
import com.tpe.domain.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {
    List<Interaction> findByCandidate(Candidate candidate);
}
