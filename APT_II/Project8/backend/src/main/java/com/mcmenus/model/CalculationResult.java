package com.mcmenus.model;

import lombok.Data;

@Data
public class CalculationResult {
    private String email;
    private String invoice;
    private String invoiceNumber;
    private String resultText;
    private double subtotal;
    private double tax;
    private double total;
    private double discount;

    // Nuevos atributos
    private String date;
    private String day;
    private double discountAmount;
    private double discountPercentage;
    private double totalAfterDiscount;
    private double taxAmount;
    private double finalTotal;
    private boolean comboDiscount;

    // Getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getInvoice() { return invoice; }
    public void setInvoice(String invoice) { this.invoice = invoice; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getResultText() { return resultText; }
    public void setResultText(String resultText) { this.resultText = resultText; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    public double getTotalAfterDiscount() { return totalAfterDiscount; }
    public void setTotalAfterDiscount(double totalAfterDiscount) { this.totalAfterDiscount = totalAfterDiscount; }

    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }

    public double getFinalTotal() { return finalTotal; }
    public void setFinalTotal(double finalTotal) { this.finalTotal = finalTotal; }

    public boolean isComboDiscount() { return comboDiscount; }
    public void setComboDiscount(boolean comboDiscount) { this.comboDiscount = comboDiscount; }
} 