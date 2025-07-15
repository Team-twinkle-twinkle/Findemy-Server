package com.founderz.findemy.domain.academy.repository;

import com.founderz.findemy.domain.academy.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
    Optional<Academy> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
