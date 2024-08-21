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

/**
 * REST controller for managing job data.
 */
@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

	private final JobService jobService;

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters.
	 * @return A ResponseEntity containing a page of job vacancies and HTTP status.
	 */
	@GetMapping
	public ResponseEntity<Page<JobEntity>> getAllJobs(Pageable pageable) {
		Page<JobEntity> jobsPage = jobService.getAllJobs(pageable);
		return ResponseEntity.ok(jobsPage);
	}

	/**
	 * Retrieves the top 10 job vacancies sorted by creation date in descending order.
	 *
	 * @return A ResponseEntity containing a list of the 10 most recent job vacancies and HTTP status.
	 */
	@GetMapping("/top10")
	public ResponseEntity<List<JobEntity>> getTop10RecentJobs() {
		List<JobEntity> top10Jobs = jobService.getTop10RecentJobs();
		return ResponseEntity.ok(top10Jobs);
	}

	/**
	 * Retrieves statistics on job vacancies by location, including the number of jobs in each city.
	 *
	 * @return A ResponseEntity containing a list of maps with job statistics and HTTP status.
	 */
	@GetMapping("/statistics")
	public ResponseEntity<List<Map<String, Object>>> getJobCountByLocation() {
		List<Map<String, Object>> jobCountByLocation = jobService.getJobCountByLocation();
		return ResponseEntity.ok(jobCountByLocation);
	}

	/**
	 * Saves a list of job DTOs to the database.
	 * <p>
	 * This endpoint processes the provided job data and updates or inserts job records into the database.
	 * </p>
	 *
	 * @param jobDto The job DTO containing a list of job records to be saved.
	 * @return A ResponseEntity with HTTP status indicating the result of the operation.
	 */
	@PostMapping("/save")
	public ResponseEntity<Void> saveJobs(@RequestBody JobDto jobDto) {
		jobService.saveJobs(jobDto.data());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Updates existing job entries in the database or inserts new ones if they don't exist.
	 * <p>
	 * This endpoint processes the provided job data and performs an update operation.
	 * If a job with the same slug already exists in the database, its details are updated.
	 * If no such job exists, a new job entry is created.
	 * </p>
	 *
	 * @param jobDto The job DTO containing a list of job records to be updated or inserted.
	 * @return A ResponseEntity with HTTP status indicating the result of the operation.
	 */
	@PutMapping("/update")
	public ResponseEntity<Void> updateJobs(@RequestBody JobDto jobDto) {
		jobService.saveJobs(jobDto.data());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}