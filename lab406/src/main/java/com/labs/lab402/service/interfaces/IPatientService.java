package com.labs.lab402.service.interfaces;

import com.labs.lab402.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface IPatientService {
    
    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    List<Patient> getPatientsByDateOfBirthRange(LocalDate start, LocalDate end);

    List<Patient> getPatientsByDoctorDepartment(String department);

    List<Patient> getPatientsByDoctorStatusOFF();

    void createPatient(Patient patient);

    Patient updatePatient(Patient patient, Long id);
}
