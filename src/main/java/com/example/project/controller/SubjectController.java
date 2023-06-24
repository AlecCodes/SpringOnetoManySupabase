package com.example.project.controller;

import java.util.List;
import java.util.Optional;
import java.lang.RuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.project.model.Subject;
import com.example.project.repository.SubjectRepository;
import com.example.project.repository.KingRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    private KingRepository kingRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/kings/{kingId}/subjects")
    public Optional<List<Subject>> getAllSubjectsByKingId(@PathVariable(value = "kingId") long kingId){
        if(!kingRepository.existsById(kingId)){
            throw new RuntimeException("Couldn't find king with id: " + kingId);
        }
        List<Subject> subjects = subjectRepository.findByKingId(kingId);
        return Optional.of(subjects);
    }

    @GetMapping("/subjects/{id}")
    public Optional<Subject> getSubjectsById(@PathVariable(value = "id") Long id){
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("COULD NOT FIND SUBJECT W ID: " + id));
        return Optional.of(subject);
    }

    //our JPA repository methods like findByID return an optional, upon which we can use the .map method to transform
    //the value of the optional with the provided lambda function (callback)
    @PostMapping("/kings/{kingId}/subjects")
    public Optional<Subject> createSubject(@PathVariable(value="kingId") long kingId, @RequestBody Subject subjectRequest){
        Subject subject = kingRepository.findById(kingId).map(king -> {
            subjectRequest.setKing(king);
            return subjectRepository.save(subjectRequest);
        }).orElseThrow(() -> new RuntimeException("Couldn't find king with id: " + kingId));

        return Optional.of(subject);
    }


}
