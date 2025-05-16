package com.labs.lab402.controller.impl;

import com.labs.lab402.controller.interfaces.IPatientController;
import com.labs.lab402.model.Patient;
import com.labs.lab402.repository.PatientRepository;
import com.labs.lab402.service.interfaces.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    IPatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/patients/dob")
    public List<Patient> getPatientsByDateOfBirthRange(@PathVariable LocalDate start, LocalDate end) {
        return patientService.getPatientsByDateOfBirthRange(start, end);
    }

    @GetMapping("/patients/admitted-by/department/{department}")
    public List<Patient> getPatientsByDoctorDepartment(@PathVariable String department) {
        return patientService.getPatientsByDoctorDepartment(department);
    }

    @GetMapping("/patients/admitted-by/status/OFF")
    public List<Patient> getPatientsByDoctorStatusOFF() {
        return patientService.getPatientsByDoctorStatusOFF();
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPatient(@RequestBody @Valid Patient patient) {
        patientService.createPatient(patient);
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable Long id) {
        return patientService.updatePatient(patient, id);
    }










}
