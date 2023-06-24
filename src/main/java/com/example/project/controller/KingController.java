package com.example.project.controller;

//Arraylist is a resizeable array
import java.util.ArrayList;
import java.util.List;

//Autowired automatically hooks up dependencies when an instance of the annotated class is created
import org.springframework.beans.factory.annotation.Autowired;
//This is what throws status codes to the client.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.lang.RuntimeException;
import com.example.project.model.King;
import com.example.project.repository.KingRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class KingController {
    @Autowired
    KingRepository kingRepository;

    //Here we will use request param instead of path variable
    @GetMapping("/kings")
    public Optional<List<King>> getAllKings(@RequestParam(required = false) String title){
        List<King> kings = new ArrayList<King>();

        if (title == null)
            //findAll returns a collection (object that represents a collection of objects)
            //Would this work w lambda? lets try!
            kingRepository.findAll().forEach(kings::add);
        else
            kingRepository.findByTitleContaining(title).forEach(kings::add);

        return Optional.of(kings);
    }
}
