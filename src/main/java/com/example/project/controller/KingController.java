package com.example.project.controller;

//Arraylist is a resizeable array
import java.util.ArrayList;
import java.util.List;

//Autowired automatically hooks up dependencies when an instance of the annotated class is created
import org.apache.coyote.Request;
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

    public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message){
            super(message);
        }
    }
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

    @GetMapping("/kings/{id}")
    public Optional<King> getKingById(@PathVariable("id") long id){
        King king = kingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find King with id = " + id));
        return Optional.of(king);

    }

    @PostMapping("/kings")
    public Optional<King> createKing(@RequestBody King king){
        King _king = kingRepository.save(new King(king.getTitle(), king.getName(), king.getIsHeathen()));
        return Optional.of(_king);
    }

    @PutMapping("/kings/{id}")
    public Optional<King> updateKing(@PathVariable("id") long id, @RequestBody King king){
        King _king = kingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find King with id: " + id));

        _king.setTitle(king.getTitle());
        _king.setName(king.getName());
        _king.setIsHeathen(king.getIsHeathen());

        return Optional.of(kingRepository.save(_king));
    }

    @DeleteMapping("/kings/{id}")
    public Optional<King> regicide(@PathVariable("id") long id){
        King king = kingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find King with id = " + id));
        kingRepository.deleteById(id);
        return Optional.of(king);
    }



}
