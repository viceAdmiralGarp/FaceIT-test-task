package com.mmdev.faceittesttask.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mmdev.faceittesttask.model.JobDto;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JobApiClient {
    private final RestTemplate restTemplate;

    @Value("${job.api.url}")
    private String jobApiUrl;

    public JobDto findJobs() {
        ResponseEntity<JobDto> response = restTemplate.getForEntity(jobApiUrl, JobDto.class);
        return response.getBody();
    }

    public JobDto findTop10ByRelevance() {
        ResponseEntity<JobDto> response = restTemplate.getForEntity(jobApiUrl, JobDto.class, Map.of("sort_by", "relevance"));
        return response.getBody();
    }
}
