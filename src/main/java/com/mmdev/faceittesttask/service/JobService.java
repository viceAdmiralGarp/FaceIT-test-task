package com.mmdev.faceittesttask.service;

import com.mmdev.faceittesttask.entity.JobEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import com.mmdev.faceittesttask.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobService {

	private final JobRepository jobRepository;

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters
	 * @return A page of job vacancies that match the specified parameters
	 */
	public Page<JobEntity> getAllJobs(Pageable pageable) {
		return jobRepository.findAll(pageable);
	}

	/**
	 * Retrieves the top 10 job vacancies sorted by creation date in descending order.
	 * <p>
	 * This method returns the 10 most recent job vacancies based on their creation date.
	 * </p>
	 *
	 * @return A list of the 10 most recent job vacancies
	 */
	public List<JobEntity> getTop10RecentJobs() {
		return jobRepository.findTop10ByOrderByCreatedAtDesc();
	}

	/**
	 * Retrieves statistics on job vacancies by location, including the number of jobs in each city.
	 * <p>
	 * This method groups job vacancies by location and counts the number of jobs in each location.
	 * </p>
	 *
	 * @return A list of maps where each map contains information about a location and the number of jobs in that location.
	 *         Map keys: "city" (the city), "count" (the number of jobs)
	 */
	public List<Map<String, Object>> getJobCountByLocation() {
		return jobRepository.findJobCountByLocation();
	}
}
