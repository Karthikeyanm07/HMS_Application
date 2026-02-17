package com.example.Backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.module.Patient;
import com.example.Backend.module.User;
import com.example.Backend.repository.PatientRepository;
import com.example.Backend.repository.UserRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public Patient addPatient(Long userId, Patient patient) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("User not found with id: " + userId));

        patient.setUser(user);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("Patient not found with id: " + id));
    }
}

