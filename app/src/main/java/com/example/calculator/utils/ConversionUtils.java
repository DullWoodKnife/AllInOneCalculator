package com.example.calculator.utils;

import com.example.calculator.models.ConversionUnit;
import java.util.ArrayList;
import java.util.List;

public class ConversionUtils {

    public static List<ConversionUnit> getResistanceUnits() {
        List<ConversionUnit> units = new ArrayList<>();
        units.add(new ConversionUnit("欧姆", "Ω", 1.0));
        units.add(new ConversionUnit("千欧", "kΩ", 1000.0));
        units.add(new ConversionUnit("兆欧", "MΩ", 1000000.0));
        units.add(new ConversionUnit("毫欧", "mΩ", 0.001));
        units.add(new ConversionUnit("微欧", "μΩ", 0.000001));
        return units;
    }

    public static List<ConversionUnit> getFuelConsumptionUnits() {
        List<ConversionUnit> units = new ArrayList<>();
        units.add(new ConversionUnit("升/百公里", "L/100km", 1.0));
        units.add(new ConversionUnit("英里/加仑(美)", "MPG(US)", 235.215));
        units.add(new ConversionUnit("英里/加仑(英)", "MPG(UK)", 282.481));
        units.add(new ConversionUnit("公里/升", "km/L", 100.0));
        units.add(new ConversionUnit("升/公里", "L/km", 0.01));
        return units;
    }

    public static List<ConversionUnit> getEnergyUnits() {
        List<ConversionUnit> units = new ArrayList<>();
        units.add(new ConversionUnit("焦耳", "J", 1.0));
        units.add(new ConversionUnit("千焦", "kJ", 1000.0));
        units.add(new ConversionUnit("卡路里", "cal", 4.184));
        units.add(new ConversionUnit("千卡", "kcal", 4184.0));
        units.add(new ConversionUnit("瓦时", "Wh", 3600.0));
        units.add(new ConversionUnit("千瓦时", "kWh", 3600000.0));
        units.add(new ConversionUnit("电子伏特", "eV", 1.602176634e-19));
        return units;
    }

    public static double convert(double value, ConversionUnit from, ConversionUnit to) {
        double baseValue = value * from.getFactor();
        return baseValue / to.getFactor();
    }
}
