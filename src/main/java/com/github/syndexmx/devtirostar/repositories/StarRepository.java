package com.github.syndexmx.devtirostar.repositories;

import com.github.syndexmx.devtirostar.domain.StarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<StarEntity, String> {
}
