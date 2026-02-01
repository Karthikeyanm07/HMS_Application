package com.example.Backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.module.Appointment;
import com.example.Backend.module.Doctor;
import com.example.Backend.module.Patient;
import com.example.Backend.repository.AppointmentRepository;
import com.example.Backend.repository.DoctorRepository;
import com.example.Backend.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Appointment bookAppointment(
            Long patientId,
            Long doctorId,
            LocalDate date,
            LocalTime time
    ) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        boolean alreadyBooked =
                appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTime(
                        doctor, date, time);

        if (alreadyBooked) {
            throw new RuntimeException("Doctor already booked for this slot");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setStatus("BOOKED");

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

