package com.hamada.library.repositories;


import com.hamada.library.domain.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<PatronEntity , Long> {
}
