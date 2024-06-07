package com.prakash.clinicals.controller;

import com.prakash.clinicals.model.ClinicalData;
import com.prakash.clinicals.model.Patient;
import com.prakash.clinicals.repos.PatientRepository;
import com.prakash.clinicals.utils.BMICalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    Map<String, String> filterMap = new HashMap<>();

    @GetMapping("/patients/{id}")
    public Patient getPatientsById(@PathVariable("id") int id) {
        return patientRepository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping(value = "/patients/analyze/{id}")
    public Patient analyse(@PathVariable("id") int id) {
        Patient patient = patientRepository.findById(id).get();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (ClinicalData eachEntry : duplicateClinicalData) {
            if (filterMap.containsKey(eachEntry.getComponentName())) {
                clinicalData.remove(eachEntry);
                continue;
            } else {
                filterMap.put(eachEntry.getComponentName(), null);
            }
            BMICalculatorUtils.calculateBMI(clinicalData, eachEntry);
        }
        filterMap.clear();
        return patient;
    }
}
