package com.hamada.library.services.impl;

import com.hamada.library.domain.PatronEntity;
import com.hamada.library.repositories.PatronRepository;
import com.hamada.library.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronServiceImpl(final PatronRepository patronRepository){
        this.patronRepository = patronRepository;
    }

    @Override
    public PatronEntity create(PatronEntity patron) {
        return patronRepository.save(patron);
    }

    @Override
    @Cacheable("patrons")
    public Optional<PatronEntity> find(Long ID) {
        return patronRepository.findById(ID);
    }

    @Override
    public List<PatronEntity> listPatrons() {
        return patronRepository.findAll();
    }

    @Override
    public Boolean isPatronExists(Long ID) {
        return patronRepository.existsById(ID);
    }

    @Override
    public void deleteById(Long ID) {
        patronRepository.deleteById(ID);
    }
}
