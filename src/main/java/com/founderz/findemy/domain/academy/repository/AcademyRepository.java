package com.founderz.findemy.domain.academy.repository;

import com.founderz.findemy.domain.academy.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
    Optional<Academy> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
    @Query("SELECT a FROM tbl_academy a LEFT JOIN FETCH a.subjects")
    List<Academy> findAllWithSubjects(); // grades만 fetch
}
