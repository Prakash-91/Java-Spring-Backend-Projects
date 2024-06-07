package com.prakash.clinicals.controller;

import com.prakash.clinicals.dto.ClinicalDataDto;
import com.prakash.clinicals.model.ClinicalData;
import com.prakash.clinicals.model.Patient;
import com.prakash.clinicals.repos.ClinicalDataRepository;
import com.prakash.clinicals.repos.PatientRepository;
import com.prakash.clinicals.utils.BMICalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalController {

    private PatientRepository patientRepository;
    private ClinicalDataRepository clinicalDataRepository;

    @Autowired
    ClinicalController(PatientRepository patientRepository, ClinicalDataRepository clinicalDataRepository) {
        this.patientRepository = patientRepository;
        this.clinicalDataRepository = clinicalDataRepository;
    }

    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataDto clinicalDataDto) {
        Patient patient = patientRepository.findById(clinicalDataDto.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setPatient(patient);
        clinicalData.setComponentName(clinicalDataDto.getComponentName());
        clinicalData.setComponentValue(clinicalDataDto.getComponentValue());
        return clinicalDataRepository.save(clinicalData);
    }

    @GetMapping("/clinicals/{patientId}/{componentName}")
    public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
                                              @PathVariable("componentName") String componentName) {
        if(componentName.equalsIgnoreCase("bmi")){
            componentName = "hw";
        }
        List<ClinicalData> clinicalData = clinicalDataRepository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (ClinicalData eachEntry : duplicateClinicalData) {
            BMICalculatorUtils.calculateBMI(clinicalData, eachEntry);
        }
        return clinicalData;
    }
}
