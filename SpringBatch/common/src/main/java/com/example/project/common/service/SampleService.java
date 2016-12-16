package com.example.project.common.service;

import com.example.project.common.entity.SampleEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for {@link SampleEntity} entities.
 *
 * @author mlglenn.
 */
public interface SampleService {

    Optional<SampleEntity> findById(Long id);

    List<SampleEntity> findAll();

    SampleEntity save(SampleEntity entity);
}
