package com.tpe.service;

import com.tpe.domain.Interaction;

import java.util.List;

public interface InteractionService {

    List<Interaction> getAllInteractions();
    Interaction findInteractionById(Long id);

    void saveInteraction(Interaction interaction);

    Interaction updateInteraction(Long id,Interaction interaction);

    void deleteInteraction(Long id);


}
