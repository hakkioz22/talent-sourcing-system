package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide fullName!")
    @Column(length = 100,nullable = false)
    private String fullName;

    @Column
    @NotBlank(message = "Please provide contact information!")
    private String contactInformation;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;

    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    private List<Interaction> interactionList;



}
