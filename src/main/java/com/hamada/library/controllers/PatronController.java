package com.hamada.library.controllers;


import com.hamada.library.domain.PatronEntity;
import com.hamada.library.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(final PatronService patronService){
        this.patronService = patronService;
    }


    @PostMapping("/api/patrons")
    public ResponseEntity<PatronEntity> create(@RequestBody final PatronEntity patronEntity){
        final PatronEntity savedPatronEntity=  patronService.create(patronEntity);
        return new ResponseEntity<PatronEntity>(savedPatronEntity , HttpStatus.CREATED);
    }


    @GetMapping("/api/patrons/{id}")
    public ResponseEntity<PatronEntity> find(@PathVariable Long id){
        Optional<PatronEntity> patronEntity = patronService.find(id);
        return patronEntity.map(patron -> new ResponseEntity<PatronEntity>(patron , HttpStatus.OK))
                .orElse(new ResponseEntity<PatronEntity>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/patrons")
    public ResponseEntity<List<PatronEntity>> list(){
        return new ResponseEntity<List<PatronEntity>>(patronService.listPatrons() , HttpStatus.OK);
    }

    @PutMapping("/api/patrons/{id}")
    public ResponseEntity<PatronEntity> update(
            @RequestBody final PatronEntity patronEntity,
            @PathVariable Long id
    ){

        if(!patronService.isPatronExists(id)){
            return new ResponseEntity<PatronEntity>(patronEntity , HttpStatus.NOT_FOUND);
        }

        patronEntity.setID(id);
        PatronEntity savedPatronEntity = patronService.create(patronEntity);
        return new ResponseEntity<PatronEntity>(savedPatronEntity , HttpStatus.OK);

    }

    @DeleteMapping("/api/patrons/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if (!patronService.isPatronExists(id)){
            return new ResponseEntity<Boolean>(false , HttpStatus.NOT_FOUND);
        }
        patronService.deleteById(id);

        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }

}
