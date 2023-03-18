package com.tpe.controller;

import com.tpe.domain.Candidate;
import com.tpe.domain.Interaction;
import com.tpe.service.CandidateService;
import com.tpe.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates/{candidateId}/interactions")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @Autowired
    private CandidateService candidateService;


    //Get an Interaction by id
    @GetMapping("/{id}")
    public ResponseEntity<Interaction> getInteractionById(@PathVariable Long id){
        Interaction foundInteraction = interactionService.findInteractionById(id);
        return new ResponseEntity<>(foundInteraction,HttpStatus.OK);
    }

    //Create a new interaction for a candidate
    @PostMapping
    public ResponseEntity<Map<String,String>> createInteraction(@PathVariable Long candidateId, @RequestBody Interaction interaction){
        Candidate candidate = candidateService.findCandidateById(candidateId);
        interaction.setCandidate(candidate);

        interactionService.saveInteraction(interaction);

        Map<String,String> map = new HashMap<>();

        map.put("message","Interaction created successfully!");
        map.put("success","true");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }

    //Update an existing interaction
    @PutMapping("/{interactionId}")
    public ResponseEntity<Interaction> updateInteraction(
            @PathVariable Long candidateId,
            @PathVariable Long interactionId,
            @RequestBody Interaction interactionDetails) {

        Candidate candidate = candidateService.findCandidateById(candidateId);
        Interaction updatedInteraction = interactionService.updateInteraction(interactionId, interactionDetails);
        return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);
    }

    //Delete an existing interaction by id
    @DeleteMapping("/{interactionId}")
    public ResponseEntity<String> deleteInteraction(@PathVariable Long interactionId){
        interactionService.deleteInteraction(interactionId);
        return new ResponseEntity<>("Interaction with id " + interactionId + " has been deleted!",HttpStatus.OK);
    }
}
