package com.hamada.library.controllers;


import com.hamada.library.domain.PatronEntity;
import com.hamada.library.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
