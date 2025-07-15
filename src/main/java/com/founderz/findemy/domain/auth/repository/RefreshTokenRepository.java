package com.founderz.findemy.domain.auth.repository;

import com.founderz.findemy.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
