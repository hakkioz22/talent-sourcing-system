package com.tpe.service;

import com.tpe.domain.Interaction;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionServiceImpl implements InteractionService{

    @Autowired
    private InteractionRepository interactionRepository;

    //Get All Interactions
    @Override
    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    //Get interaction by id
    @Override
    public Interaction findInteractionById(Long id) {
        return interactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Interaction not found with by id: " + id));
    }

    //Create a new interaction
    @Override
    public void saveInteraction(Interaction interaction) {
        interactionRepository.save(interaction);
    }

    //Update an existing interaction
    @Override
    public Interaction updateInteraction(Long id, Interaction interactionDetails) {
        Optional<Interaction> optionalInteraction = interactionRepository.findById(id);

        if (optionalInteraction.isPresent()) {
            Interaction interaction = optionalInteraction.get();
            interaction.setType(interactionDetails.getType());
            interaction.setContent(interactionDetails.getContent());
            interaction.setDate(interactionDetails.getDate());
            interaction.setCandidateResponded(interactionDetails.isCandidateResponded());

            return interactionRepository.save(interaction);
        } else {
            throw new ResourceNotFoundException("Interaction with id " + id + " not found.");
        }
    }

    //Delete an existing interaction
    @Override
    public void deleteInteraction(Long id) {
        Interaction interaction = interactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction not found with id: " + id));

        interactionRepository.delete(interaction);
    }
}
