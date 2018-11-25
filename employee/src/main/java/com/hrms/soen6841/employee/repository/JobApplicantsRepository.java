package com.hrms.soen6841.employee.repository;

import com.hrms.soen6841.employee.model.JobApplicants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicantsRepository extends JpaRepository<JobApplicants, Integer> {
}
