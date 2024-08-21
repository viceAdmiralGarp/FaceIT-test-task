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

    private final static String PAGE_QUERY_PARAM = "page";
    private final static String SORT_BY_QUERY_PARAM = "sort_by";

    public JobDto findJobs() {
        ResponseEntity<JobDto> response = restTemplate.getForEntity(jobApiUrl, JobDto.class);
        return response.getBody();
    }

    public JobDto findJobs(Integer pageNumber) {
        ResponseEntity<JobDto> response = restTemplate.getForEntity(jobApiUrl, JobDto.class, Map.of(PAGE_QUERY_PARAM, pageNumber));
        return response.getBody();
    }

    public JobDto findTop10ByRelevance() {
        ResponseEntity<JobDto> response = restTemplate.getForEntity(jobApiUrl, JobDto.class, Map.of(SORT_BY_QUERY_PARAM, "relevance"));
        return response.getBody();
    }
}
