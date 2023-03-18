package com.tpe.service;

import com.tpe.domain.Candidate;
import com.tpe.domain.Interaction;

import java.util.List;

public interface CandidateService {

    List<Candidate> getAllCandidate();
    Candidate findCandidateById(Long id);
    void saveCandidate(Candidate candidate);
    Candidate updateCandidate(Long id,Candidate candidate);
    void deleteCandidate(Long id);
    List<Interaction> findInteractionByCandidateId(Long id);

}
