package com.mmdev.faceittesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobDto(
		@JsonProperty("data") List<Job> data
) {

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