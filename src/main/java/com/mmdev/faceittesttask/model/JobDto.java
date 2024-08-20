package com.mmdev.faceittesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

/**
 * Data Transfer Object (DTO) for job data received from the API.
 * <p>
 * This class represents the structure of the response payload from the job API.
 * It contains a list of job details encapsulated in the nested {@link Job} record.
 * </p>
 *
 * @param data A list of job records containing job details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record JobDto(
		@JsonProperty("data") List<Job> data
) {

	/**
	 * Represents a single job entry from the job API.
	 * <p>
	 * This record encapsulates the details of a job including its slug, company name,
	 * title, description, remote status, URL, tags, job types, location, and creation date.
	 * </p>
	 *
	 * @param slug        A unique identifier for the job.
	 * @param companyName The name of the company offering the job.
	 * @param title       The title of the job position.
	 * @param description A detailed description of the job.
	 * @param remote      Indicates whether the job is remote.
	 * @param url         The URL for the job listing.
	 * @param tags        A list of tags associated with the job.
	 * @param jobTypes    A list of job types (e.g., full-time, part-time).
	 * @param location    The location where the job is based.
	 * @param createdAt   The date and time when the job was created.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Job(
			@JsonProperty("slug") String slug,
			@JsonProperty("company_name") String companyName,
			@JsonProperty("title") String title,
			@JsonProperty("description") String description,
			@JsonProperty("remote") boolean remote,
			@JsonProperty("url") String url,
			@JsonProperty("tags") List<String> tags,
			@JsonProperty("job_types") List<String> jobTypes,
			@JsonProperty("location") String location,
			@JsonProperty("created_at") Instant createdAt
	) {}
}