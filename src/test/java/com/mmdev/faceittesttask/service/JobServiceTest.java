package com.mmdev.faceittesttask.service;

import com.mmdev.faceittesttask.client.JobApiClient;
import com.mmdev.faceittesttask.entity.JobEntity;
import com.mmdev.faceittesttask.model.JobDto;
import com.mmdev.faceittesttask.repository.JobEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JobServiceTest {

    @Mock
    private JobEntityRepository jobEntityRepository;

    @Mock
    private JobApiClient jobApiClient;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAndSaveJobs() {
        JobDto jobDto = mock(JobDto.class);
        when(jobApiClient.findJobs()).thenReturn(jobDto);
        when(jobDto.data()).thenReturn(Collections.emptyList());

        jobService.fetchAndSaveJobs();

        verify(jobApiClient).findJobs();
        verify(jobDto).data();
    }

    @Test
    public void testSaveOrUpdateJobs() {
        JobEntity jobEntity = mock(JobEntity.class);
        when(jobEntity.getSlug()).thenReturn("slug");
        when(jobEntityRepository.findBySlug("slug")).thenReturn(Optional.empty());

        jobService.saveAll(Collections.singletonList(jobEntity));

        verify(jobEntityRepository).findBySlug("slug");
        verify(jobEntityRepository).save(jobEntity);
    }

    @Test
    public void testSaveJobs() {
        JobDto.Job job = mock(JobDto.Job.class);
        when(job.slug()).thenReturn("slug");

        jobService.saveJobs(Collections.singletonList(job));

        verify(jobEntityRepository).findBySlug("slug");
    }

    @Test
    public void testGetAllJobs() {
        Pageable pageable = mock(Pageable.class);
        Page<JobEntity> page = mock(Page.class);
        when(jobEntityRepository.findAll(pageable)).thenReturn(page);

        Page<JobEntity> result = jobService.getAllJobs(pageable);

        assertEquals(page, result);
        verify(jobEntityRepository).findAll(pageable);
    }

    @Test
    public void testFindTop10ByRelevance() {
        JobDto jobDto = mock(JobDto.class);
        when(jobApiClient.findTop10ByRelevance()).thenReturn(jobDto);

        JobDto result = jobService.findTop10ByRelevance();

        assertEquals(jobDto, result);
        verify(jobApiClient).findTop10ByRelevance();
    }

    @Test
    public void testGetJobCountByLocation() {
        List<Map<String, Object>> expected = Collections.emptyList();
        when(jobEntityRepository.findJobCountByLocation()).thenReturn(expected);

        List<Map<String, Object>> result = jobService.getJobCountByLocation();

        assertEquals(expected, result);
        verify(jobEntityRepository).findJobCountByLocation();
    }
}