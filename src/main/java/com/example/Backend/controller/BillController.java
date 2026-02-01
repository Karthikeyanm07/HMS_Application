package com.example.Backend.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Backend.module.Bill;
import com.example.Backend.service.BillService;



@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billingService;

    @PostMapping("/generate")
    public ResponseEntity<Bill> generateBill(
            @RequestParam Long appointmentId,
            @RequestParam double amount
    ) {
        return ResponseEntity.ok(
                billingService.generateBill(appointmentId, amount)
        );
    }
}
