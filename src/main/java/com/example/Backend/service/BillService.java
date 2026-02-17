package com.example.Backend.service;

import com.example.Backend.module.Appointment;
import com.example.Backend.module.Bill;
import com.example.Backend.repository.AppointmentRepository;
import com.example.Backend.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Bill generateBill(Long appointmentId, double amount) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        Bill bill = new Bill();
        bill.setAppointment(appointment);
        bill.setTotalAmount(amount);
        bill.setPaymentStatus("UNPAID");

        return billRepository.save(bill);
    }
}
