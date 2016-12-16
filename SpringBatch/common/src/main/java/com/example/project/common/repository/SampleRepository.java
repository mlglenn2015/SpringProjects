package com.example.project.common.repository;

import com.example.project.common.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO Interface for {@link com.example.project.common.entity.SampleEntity} entities.
 *
 * @author mlglenn
 */
@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

    Optional<SampleEntity> findByIndex(Long index);

    List<SampleEntity> findAll();
}
