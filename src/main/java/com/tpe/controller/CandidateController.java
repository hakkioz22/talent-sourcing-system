package com.tpe.controller;

import com.tpe.domain.Candidate;
import com.tpe.domain.Interaction;
import com.tpe.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // Get all candidates
    @GetMapping()
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidate();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    // Get candidate by id
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.findCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    // Create a new candidate
    @PostMapping
    public ResponseEntity<Map<String,String>> createCandidate(@RequestBody Candidate candidate) {
        candidateService.saveCandidate(candidate);

        Map<String,String> map = new HashMap<>();
        map.put("message","Candidate created successfully");
        map.put("success","true");

        return new ResponseEntity<>(map,HttpStatus.CREATED);

    }

    // Update an existing candidate
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
        candidateService.updateCandidate(id,updatedCandidate);

        Map<String,String> map = new HashMap<>();
        map.put("message","Candidate updated successfully");
        map.put("success","true");

        return ResponseEntity.ok(map);

    }

    // Delete a candidate by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with id " + id + " has been deleted.", HttpStatus.OK);
    }

    //Get all interactions for specific candidate
    @GetMapping("{id}/interactions")
    public List<Interaction> getCandidateInteractions(@PathVariable Long id){
        return candidateService.findInteractionByCandidateId(id);
    }

}
