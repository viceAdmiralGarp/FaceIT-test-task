package com.mmdev.faceittesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobResponse {
	private List<Job> data;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Job {

		private String slug;

		@JsonProperty("company_name")
		private String companyName;

		private String title;

		@JsonProperty("description")
		private String description;

		private boolean remote;
		private String url;
		private List<String> tags;

		@JsonProperty("job_types")
		private List<String> jobTypes;

		private String location;

		@JsonProperty("created_at")
		private long createdAt;
	}
}
