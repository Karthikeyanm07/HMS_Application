package com.example.Backend.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {
public	Bill(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private double totalAmount;
    private String paymentStatus; // PAID / UNPAID

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Bill(Long billId, double totalAmount, String paymentStatus, Appointment appointment) {
		super();
		this.billId = billId;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.appointment = appointment;
	}

    // getters & setters
}

