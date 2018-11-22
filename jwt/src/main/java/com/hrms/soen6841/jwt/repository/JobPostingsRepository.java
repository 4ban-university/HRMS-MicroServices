package com.hrms.soen6841.jwt.repository;

import com.hrms.soen6841.jwt.model.JobPostings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingsRepository extends JpaRepository<JobPostings, Integer> {
}
