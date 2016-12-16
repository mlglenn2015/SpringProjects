package com.example.project.common.service.impl;

import com.example.project.common.entity.SampleEntity;
import com.example.project.common.repository.SampleRepository;
import com.example.project.common.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of the {@link SampleService} interface.
 *
 * @author mlglenn.
 */
@Service
public class SampleServiceImpl implements SampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);
    private SampleRepository sampleRepository;


    @Override
    public Optional<SampleEntity> findById(final Long id) {
        LOGGER.debug("SampleServiceImpl.findById({})", id);
        return sampleRepository.findByIndex(id);
    }

    @Override
    public List<SampleEntity> findAll() {
        LOGGER.debug("SampleServiceImpl.findAll()");
        return sampleRepository.findAll();
    }

    @Override
    public SampleEntity save(final SampleEntity entity) {
        LOGGER.debug("SampleServiceImpl.save({})", entity);
        return sampleRepository.saveAndFlush(entity);
    }

}
