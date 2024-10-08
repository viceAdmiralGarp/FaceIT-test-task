package com.mmdev.faceittesttask.service;

import static java.util.concurrent.TimeUnit.HOURS;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mmdev.faceittesttask.client.JobApiClient;
import com.mmdev.faceittesttask.entity.JobEntity;
import com.mmdev.faceittesttask.model.JobDto;
import com.mmdev.faceittesttask.repository.JobEntityRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing job data.
 */
@Service
@RequiredArgsConstructor
public class JobService {

	private final JobEntityRepository jobEntityRepository;
	private final JobApiClient jobApiClient;

	/**
	 * Fetches job data from the external API and saves or updates the job entries in the database.
	 * <p>
	 * This method is scheduled to run every hour (3600000 milliseconds). It retrieves job data from the specified
	 * API URL, converts the retrieved DTOs to entity objects, and then updates or saves these entities in the database.
	 * </p>
	 */
	@Scheduled(fixedRate = 1, timeUnit = HOURS)
	public void fetchAndSaveJobs() {
		JobDto jobDto = jobApiClient.findJobs();
		convertAndSave(jobDto);
	}

	/**
	 * Fetches job data from the external API and saves the job entries in the database.
	 * <p>
	 * This method retrieves job data from the specified API URL by page number, converts the retrieved DTOs to entity objects,
	 * and then updates or saves these entities in the database.
	 * </p>
	 *
	 * @param pageNumber The page number from which to fetch job data.
	 */
	public void fetchAndSaveJobs(Integer pageNumber) {
		JobDto jobDto = jobApiClient.findJobs(pageNumber);
		convertAndSave(jobDto);
	}

	/**
	 * Saves new job entities to the database or updates existing ones.
	 * <p>
	 * This method iterates through the list of new job entities and stores them into DB.
	 * </p>
	 *
	 * @param newJobs A list of new job entities to be saved or updated.
	 */
	public void saveAll(List<JobEntity> newJobs) {
		jobEntityRepository.saveAll(newJobs);
	}

	/**
	 * Saves a list of job DTOs to the database.
	 * <p>
	 * This method converts the provided job DTOs to job entities and then saves or updates them in the database.
	 * </p>
	 *
	 * @param jobs List of job DTOs to be saved.
	 */
	public void saveJobs(List<JobDto.Job> jobs) {
		List<JobEntity> jobEntities = jobs.stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());

		saveAll(jobEntities);
	}

	private List<JobEntity> convertResponse(JobDto jobDto) {
		return jobDto.data().stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
	}

	private void convertAndSave(JobDto jobDto) {
		if (jobDto != null) {
			List<JobEntity> newJobs = convertResponse(jobDto);
			saveAll(newJobs);
		}
	}

	/**
	 * Converts a {@link JobDto.Job} object to a {@link JobEntity} object.
	 * <p>
	 * This method takes a job DTO object and maps its fields to a new job entity. This conversion is necessary
	 * to save the job data in the database as a {@link JobEntity} instance.
	 * </p>
	 *
	 * @param jobDTO The job DTO object to be converted.
	 * @return The corresponding {@link JobEntity} object.
	 */
	private JobEntity convertToEntity(JobDto.Job jobDTO) {
		return JobEntity.builder()
				.slug(jobDTO.slug())
				.companyName(jobDTO.companyName())
				.title(jobDTO.title())
				.description(jobDTO.description())
				.remote(jobDTO.remote())
				.url(jobDTO.url())
				.tags(jobDTO.tags())
				.jobTypes(jobDTO.jobTypes())
				.location(jobDTO.location())
				.createdAt(jobDTO.createdAt())
				.build();
	}

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters.
	 * @return A page of job vacancies that match the specified parameters.
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
	 * @return A list of the 10 most recent job vacancies.
	 */
	public JobDto findTop10ByRelevance() {
		return jobApiClient.findTop10ByRelevance();
	}

	/**
	 * Retrieves statistics on job vacancies by location, including the number of jobs in each city.
	 * <p>
	 * This method groups job vacancies by location and counts the number of jobs in each location.
	 * </p>
	 *
	 * @return A list of maps where each map contains information about a location and the number of jobs in that location.
	 *         Map keys: "city" (the city), "count" (the number of jobs).
	 */
	public List<Map<String, Object>> getJobCountByLocation() {
		return jobEntityRepository.findJobCountByLocation();
	}
}