package com.tpe.service;

import com.tpe.domain.Candidate;
import com.tpe.domain.Interaction;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CandidateRepository;
import com.tpe.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService{

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private InteractionRepository interactionRepository;

    @Override
    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findCandidateById(Long id) {
        return candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate not found with by id: " + id));
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {

        return candidateRepository.findById(id)
                .map(candidate->{candidate.setFullName(updatedCandidate.getFullName());
                candidate.setContactInformation(updatedCandidate.getContactInformation());
                candidate.setStatus(updatedCandidate.getStatus());
                return candidateRepository.save(candidate);
                }).orElseThrow(()-> new ResourceNotFoundException("Candidate not found with id" + id));
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public List<Interaction> findInteractionByCandidateId(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if(candidate.isPresent()){
            return interactionRepository.findByCandidate(candidate.get());
        }else {
            throw new ResourceNotFoundException("Candidate with id " +id+" not found!");
        }
    }
}
