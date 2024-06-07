package com.prakash.clinicals.repos;

import com.prakash.clinicals.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
