package com.github.syndexmx.devtirostar.repositories;

import com.github.syndexmx.devtirostar.domain.ConstellationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstellationRepository extends JpaRepository<ConstellationEntity, String> {
}
