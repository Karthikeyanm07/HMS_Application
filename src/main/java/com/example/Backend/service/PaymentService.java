package com.example.Backend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.module.Bill;
import com.example.Backend.module.Payment;
import com.example.Backend.repository.BillRepository;
import com.example.Backend.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BillRepository billRepository;

    public Payment makePayment(Long billId, String paymentMethod) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new com.example.Backend.exception.ResourceNotFoundException("Bill not found with id: " + billId));

        if ("PAID".equals(bill.getPaymentStatus())) {
            throw new RuntimeException("Bill is already paid");
        }

        Payment payment = new Payment();
        payment.setBill(bill);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(bill.getTotalAmount());

        bill.setPaymentStatus("PAID");
        billRepository.save(bill);

        return paymentRepository.save(payment);
    }
}
