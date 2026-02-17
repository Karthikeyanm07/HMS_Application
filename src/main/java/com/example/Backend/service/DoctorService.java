package com.example.Backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.module.Doctor;
import com.example.Backend.module.User;
import com.example.Backend.repository.DoctorRepository;
import com.example.Backend.repository.UserRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public Doctor addDoctor(Long userId, Doctor doctor) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("User not found with id: " + userId));

        doctor.setUser(user);
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("Doctor not found with id: " + id));
    }
}
