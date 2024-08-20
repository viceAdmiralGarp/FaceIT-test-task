package com.mmdev.faceittesttask.service;

import com.mmdev.faceittesttask.entity.JobEntity;
import com.mmdev.faceittesttask.model.JobDto;
import com.mmdev.faceittesttask.repository.JobEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

	private final JobEntityRepository jobEntityRepository;


	/**
	 * Saves a list of job entities to the database.
	 *
	 * @param jobs List of job DTOs to be saved
	 */
	public void saveJobs(List<JobDto.Job> jobs) {
		List<JobEntity> jobEntities = jobs.stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());

		jobEntityRepository.saveAll(jobEntities);
	}

	/**
	 * Converts JobDto.Job to JobEntity.
	 *
	 * @param jobDTO The job DTO to convert
	 * @return The corresponding JobEntity
	 */
	private JobEntity convertToEntity(JobDto.Job jobDTO) {
		JobEntity jobEntity = new JobEntity();
		jobEntity.setSlug(jobDTO.slug());
		jobEntity.setCompanyName(jobDTO.companyName());
		jobEntity.setTitle(jobDTO.title());
		jobEntity.setDescription(jobDTO.description());
		jobEntity.setRemote(jobDTO.remote());
		jobEntity.setUrl(jobDTO.url());
		jobEntity.setTags(jobDTO.tags());
		jobEntity.setJobTypes(jobDTO.jobTypes());
		jobEntity.setLocation(jobDTO.location());
		jobEntity.setCreatedAt(jobDTO.createdAt());
		return jobEntity;
	}

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters
	 * @return A page of job vacancies that match the specified parameters
	 */
	public Page<JobEntity> getAllJobs(Pageable pageable) {
		return jobEntityRepository.findAll(pageable);
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
		return jobEntityRepository.findTop10ByOrderByCreatedAtDesc();
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
		return jobEntityRepository.findJobCountByLocation();
	}
}
