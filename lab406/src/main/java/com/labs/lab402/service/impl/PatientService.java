package com.labs.lab402.service.impl;

import com.labs.lab402.model.Patient;
import com.labs.lab402.repository.PatientRepository;
import com.labs.lab402.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            return null;
        }
    }

    public List<Patient> getPatientsByDateOfBirthRange(LocalDate start, LocalDate end) {
        List<Patient> patients = patientRepository.findByDateOfBirthBetween(start, end);
        return patients;
    }

    public List<Patient> getPatientsByDoctorDepartment(String department) {
        List<Patient> patients = patientRepository.findByAdmittedBy_Department(department);
        return patients;
    }

    public List<Patient> getPatientsByDoctorStatusOFF() {
        List<Patient> patients = patientRepository.findByAdmittedBy_Status("OFF");
        return patients;
    }

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient, Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) return null;
        return patientRepository.save(patient);
    }




}
