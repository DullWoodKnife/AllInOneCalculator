package com.example.calculator.models;

public class CalculatorModule {
    private String name;
    private String iconText;
    private int iconResId;
    private Class<?> targetActivity;

    public CalculatorModule(String name, String iconText, int iconResId, Class<?> targetActivity) {
        this.name = name;
        this.iconText = iconText;
        this.iconResId = iconResId;
        this.targetActivity = targetActivity;
    }

    public String getName() { return name; }
    public String getIconText() { return iconText; }
    public int getIconResId() { return iconResId; }
    public Class<?> getTargetActivity() { return targetActivity; }
}
