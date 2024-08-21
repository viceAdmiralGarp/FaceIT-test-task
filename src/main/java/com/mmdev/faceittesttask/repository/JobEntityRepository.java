package com.mmdev.faceittesttask.repository;

import com.mmdev.faceittesttask.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repository interface for managing JobEntity entities.
 * <p>
 * This interface provides methods for performing CRUD operations and specialized queries
 * for handling job data, including pagination, sorting, retrieving the top 10 jobs, and location statistics.
 * </p>
 */
@Repository
public interface JobEntityRepository extends JpaRepository<JobEntity, Long>, PagingAndSortingRepository<JobEntity, Long> {


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

	/**
	 * Finds a job entity by its slug.
	 *
	 * @param slug The slug of the job entity
	 * @return An optional containing the found job entity or empty if not found
	 */
	Optional<JobEntity> findBySlug(String slug);

}
