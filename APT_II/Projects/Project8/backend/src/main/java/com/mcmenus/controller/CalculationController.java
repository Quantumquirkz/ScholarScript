package com.mcmenus.controller;

import com.mcmenus.model.CalculationRequest;
import com.mcmenus.model.CalculationResult;
import com.mcmenus.service.CalculationService;
import com.mcmenus.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResult> calculate(@RequestBody CalculationRequest request) {
        CalculationResult result = calculationService.calculate(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/send-invoice")
    public void sendInvoice(@RequestBody CalculationResult result) {
        emailService.sendInvoicePdfEmail(
            result.getEmail(),
            "Factura de Pedido",
            result.getInvoice()
        );
    }
} 