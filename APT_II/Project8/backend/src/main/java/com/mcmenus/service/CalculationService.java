package com.mcmenus.service;

import com.mcmenus.model.CalculationRequest;
import com.mcmenus.model.CalculationResult;
import com.mcmenus.model.MenuItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class CalculationService {
    private static final double TAX_RATE = 0.07;
    private static final double WEEKDAY_COMBO_DISCOUNT = 0.50;
    private static final double WEEKDAY_DISCOUNT = 0.25;
    private static final double WEEKEND_COMBO_DISCOUNT = 0.15;

    public CalculationResult calculate(CalculationRequest request) {
        CalculationResult result = new CalculationResult();
        
        // Calcular subtotal
        double subtotal = request.getItems().stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
        
        // Determinar descuento
        boolean isWeekday = isWeekday(request.getDay());
        boolean hasCombo = request.getItems().stream().anyMatch(MenuItem::isCombo);
        double discountPercentage = calculateDiscountPercentage(isWeekday, hasCombo);
        
        // Calcular montos
        double discountAmount = subtotal * discountPercentage;
        double totalAfterDiscount = subtotal - discountAmount;
        double taxAmount = totalAfterDiscount * TAX_RATE;
        double finalTotal = totalAfterDiscount + taxAmount;
        
        // Generar número de factura
        String invoiceNumber = generateInvoiceNumber();
        
        // Formatear fecha
        String currentDate = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy, HH:mm", new Locale("es", "ES"))
        );
        
        // Construir texto de resultado
        String resultText = generateResultText(
            invoiceNumber,
            currentDate,
            request.getDay(),
            request.getEmail(),
            request.getItems(),
            subtotal,
            discountAmount,
            discountPercentage,
            totalAfterDiscount,
            taxAmount,
            finalTotal,
            hasCombo && isWeekday
        );
        
        // Llenar resultado
        result.setInvoiceNumber(invoiceNumber);
        result.setDate(currentDate);
        result.setDay(request.getDay());
        result.setSubtotal(subtotal);
        result.setDiscountAmount(discountAmount);
        result.setDiscountPercentage(discountPercentage);
        result.setTotalAfterDiscount(totalAfterDiscount);
        result.setTaxAmount(taxAmount);
        result.setFinalTotal(finalTotal);
        result.setResultText(resultText);
        result.setComboDiscount(hasCombo && isWeekday);
        
        return result;
    }
    
    private boolean isWeekday(String day) {
        return List.of("lunes", "martes", "miercoles", "jueves", "viernes")
                .contains(day.toLowerCase());
    }
    
    private double calculateDiscountPercentage(boolean isWeekday, boolean hasCombo) {
        if (isWeekday && hasCombo) {
            return WEEKDAY_COMBO_DISCOUNT;
        } else if (isWeekday) {
            return WEEKDAY_DISCOUNT;
        } else if (hasCombo) {
            return WEEKEND_COMBO_DISCOUNT;
        }
        return 0.0;
    }
    
    private String generateInvoiceNumber() {
        return String.format("MC-%d-%03d", 
            System.currentTimeMillis(),
            (int)(Math.random() * 1000));
    }
    
    private String generateResultText(
            String invoiceNumber,
            String date,
            String day,
            String email,
            List<MenuItem> items,
            double subtotal,
            double discountAmount,
            double discountPercentage,
            double totalAfterDiscount,
            double taxAmount,
            double finalTotal,
            boolean comboDiscount) {
            
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(60)).append("\n");
        sb.append("           FACTURA MCMENUS #").append(invoiceNumber).append("\n");
        sb.append("=".repeat(60)).append("\n\n");
        sb.append("Fecha: ").append(date).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Día: ").append(capitalizeFirstLetter(day)).append("\n\n");
        
        sb.append("ITEMS ORDENADOS:\n");
        sb.append("-".repeat(40)).append("\n");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            sb.append(String.format("%d. %s%s\n", 
                i + 1, 
                item.getName(),
                item.isCombo() ? " 🍟" : ""));
            sb.append("   ").append(item.getDescription()).append("\n");
            sb.append(String.format("   $%.2f\n\n", item.getPrice()));
        }
        
        sb.append("RESUMEN FINANCIERO:\n");
        sb.append("-".repeat(40)).append("\n");
        sb.append(String.format("Subtotal:                 $%.2f\n", subtotal));
        sb.append(String.format("Descuento aplicado:       $%.2f (%.0f%%)\n", 
            discountAmount, discountPercentage * 100));
        sb.append(String.format("Total con descuento:      $%.2f\n", totalAfterDiscount));
        sb.append(String.format("Impuesto (7%%):            $%.2f\n", taxAmount));
        sb.append("-".repeat(40)).append("\n");
        sb.append(String.format("TOTAL A PAGAR:            $%.2f\n\n", finalTotal));
        
        if (comboDiscount) {
            sb.append("🎉 ¡DESCUENTO ESPECIAL POR COMBO DEL DÍA!\n");
        }
        
        sb.append("¡Gracias por elegir McDonald's! 🍔✨\n");
        sb.append("=".repeat(60));
        
        return sb.toString();
    }
    
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
} 