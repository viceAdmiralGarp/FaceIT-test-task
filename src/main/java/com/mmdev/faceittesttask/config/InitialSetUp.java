package com.mmdev.faceittesttask.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mmdev.faceittesttask.service.JobService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitialSetUp implements ApplicationRunner {

    private final JobService jobService;

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 1; i <= 5; i++) {
            jobService.fetchAndSaveJobs(i);
        }
    }
}
