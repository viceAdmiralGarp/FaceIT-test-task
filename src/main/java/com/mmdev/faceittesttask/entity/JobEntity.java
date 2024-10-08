package com.mmdev.faceittesttask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;


/**
 * Entity class representing a job vacancy.
 * <p>
 * This entity is mapped to the "jobs" table in the database and holds information about job vacancies,
 * including details such as the company name, job title, description, location, and other relevant attributes.
 * </p>
 */
@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JobEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "slug", nullable = false)
	private String slug;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "title")
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "remote")
	private boolean remote;

	@Column(name = "url")
	private String url;

	@ElementCollection
	@CollectionTable(name = "job_tags", joinColumns = @JoinColumn(name = "job_id"))
	@Column(name = "tag")
	private List<String> tags;

	@ElementCollection
	@CollectionTable(name = "job_types", joinColumns = @JoinColumn(name = "job_id"))
	@Column(name = "job_type")
	private List<String> jobTypes;

	@Column(name = "location")
	private String location;

	@Column(name = "created_at")
	private Instant createdAt;
}
