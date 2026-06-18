package com.example.calculator.models;

public class ConversionUnit {
    private String name;
    private String symbol;
    private double factor;

    public ConversionUnit(String name, String symbol, double factor) {
        this.name = name;
        this.symbol = symbol;
        this.factor = factor;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public double getFactor() { return factor; }
}
