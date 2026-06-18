package com.example.calculator.utils;

public class CarCalculator {

    public static class CarLoanResult {
        public double monthlyPayment;
        public double totalPayment;
        public double totalInterest;
        public double downPayment;
        public double loanAmount;

        public CarLoanResult(double monthlyPayment, double totalPayment,
                             double totalInterest, double downPayment, double loanAmount) {
            this.monthlyPayment = monthlyPayment;
            this.totalPayment = totalPayment;
            this.totalInterest = totalInterest;
            this.downPayment = downPayment;
            this.loanAmount = loanAmount;
        }
    }

    public static CarLoanResult calculateLoan(double carPrice, double downPaymentPercent,
                                               int loanYears, double annualRate) {
        double downPayment = carPrice * (downPaymentPercent / 100.0);
        double loanAmount = carPrice - downPayment;
        int months = loanYears * 12;
        double monthlyRate = annualRate / 100.0 / 12.0;

        double monthlyPayment;
        if (monthlyRate == 0) {
            monthlyPayment = loanAmount / months;
        } else {
            monthlyPayment = loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months)
                    / (Math.pow(1 + monthlyRate, months) - 1);
        }

        double totalPayment = monthlyPayment * months;
        double totalInterest = totalPayment - loanAmount;

        return new CarLoanResult(
                Math.round(monthlyPayment * 100.0) / 100.0,
                Math.round(totalPayment * 100.0) / 100.0,
                Math.round(totalInterest * 100.0) / 100.0,
                Math.round(downPayment * 100.0) / 100.0,
                Math.round(loanAmount * 100.0) / 100.0
        );
    }

    public static double calculateFuelCost(double distance, double fuelConsumption,
                                            double fuelPrice) {
        double fuelNeeded = distance * fuelConsumption / 100.0;
        return Math.round(fuelNeeded * fuelPrice * 100.0) / 100.0;
    }
}
