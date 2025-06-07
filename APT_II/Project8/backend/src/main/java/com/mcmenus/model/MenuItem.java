package com.mcmenus.model;

import lombok.Data;

@Data
public class MenuItem {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean isCombo;
} 