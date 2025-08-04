package com.mcmenus.model;

import lombok.Data;
import java.util.List;

@Data
public class CalculationRequest {
    private String day;
    private List<MenuItem> items;
    private String email;
} 