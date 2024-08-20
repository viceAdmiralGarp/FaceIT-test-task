package com.mmdev.faceittesttask.controller;

import com.mmdev.faceittesttask.entity.JobEntity;
import com.mmdev.faceittesttask.model.JobDto;
import com.mmdev.faceittesttask.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

	private final JobService jobService;

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters
	 * @return A ResponseEntity containing a page of job vacancies and HTTP status
	 */
	@GetMapping
	public ResponseEntity<Page<JobEntity>> getAllJobs(Pageable pageable) {
		Page<JobEntity> jobsPage = jobService.getAllJobs(pageable);
		return ResponseEntity.ok(jobsPage);
	}

	/**
	 * Retrieves the top 10 job vacancies sorted by creation date in descending order.
	 *
	 * @return A ResponseEntity containing a list of the 10 most recent job vacancies and HTTP status
	 */
	@GetMapping("/top10")
	public ResponseEntity<List<JobEntity>> getTop10RecentJobs() {
		List<JobEntity> top10Jobs = jobService.getTop10RecentJobs();
		return ResponseEntity.ok(top10Jobs);
	}

	/**
	 * Retrieves statistics on job vacancies by location, including the number of jobs in each city.
	 *
	 * @return A ResponseEntity containing a list of maps with job statistics and HTTP status
	 */
	@GetMapping("/statistics")
	public ResponseEntity<List<Map<String, Object>>> getJobCountByLocation() {
		List<Map<String, Object>> jobCountByLocation = jobService.getJobCountByLocation();
		return ResponseEntity.ok(jobCountByLocation);
	}

	/**
	 * Saves a list of job DTOs to the database.
	 *
	 * @param jobDto The job DTOs to be saved
	 * @return A ResponseEntity with HTTP status
	 */
	@PostMapping("/save")
	public ResponseEntity<Void> saveJobs(@RequestBody JobDto jobDto) {
		jobService.saveJobs(jobDto.data());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}