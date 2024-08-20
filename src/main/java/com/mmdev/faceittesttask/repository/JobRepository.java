package com.mmdev.faceittesttask.repository;

import com.mmdev.faceittesttask.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Repository interface for managing JobEntity entities.
 * <p>
 * This interface provides methods for performing CRUD operations and specialized queries
 * for handling job data, including pagination, sorting, retrieving the top 10 jobs, and location statistics.
 * </p>
 */
@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

	/**
	 * Retrieves all job vacancies with pagination and sorting.
	 *
	 * @param pageable The pagination and sorting parameters
	 * @return A page of job vacancies that match the specified parameters
	 */
	Page<JobEntity> findAll(Pageable pageable);

	/**
	 * Retrieves the top 10 job vacancies by creation date.
	 * <p>
	 * Sorts jobs by creation date in descending order and returns the top 10.
	 * </p>
	 *
	 * @return A list of the 10 most recent job vacancies
	 */
	List<JobEntity> findTop10ByOrderByCreatedAtDesc();

	/**
	 * Retrieves statistics by location, including the number of jobs in each city.
	 * <p>
	 * Groups jobs by location and counts the number of jobs for each location.
	 * </p>
	 *
	 * @return A list of maps where each map contains information about a location and the number of jobs in that location.
	 *         Map keys: "city" (the city), "count" (the number of jobs)
	 */
	@Query("SELECT j.location AS city, COUNT(j.id) AS count FROM JobEntity j GROUP BY j.location")
	List<Map<String, Object>> findJobCountByLocation();

}
