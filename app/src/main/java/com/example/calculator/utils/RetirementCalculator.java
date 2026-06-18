package com.example.calculator.utils;

public class RetirementCalculator {

    public static class RetirementResult {
        public int yearsToRetirement;
        public int monthsToRetirement;
        public int retirementYear;
        public double totalSavings;
        public double monthlyPension;
        public String advice;

        public RetirementResult(int yearsToRetirement, int monthsToRetirement,
                                int retirementYear, double totalSavings,
                                double monthlyPension, String advice) {
            this.yearsToRetirement = yearsToRetirement;
            this.monthsToRetirement = monthsToRetirement;
            this.retirementYear = retirementYear;
            this.totalSavings = totalSavings;
            this.monthlyPension = monthlyPension;
            this.advice = advice;
        }
    }

    public static RetirementResult calculateRetirement(int currentAge, int retirementAge,
                                                        double monthlyIncome,
                                                        double monthlySavingsPercent,
                                                        double currentSavings,
                                                        double annualReturnRate) {
        int yearsToRetirement = retirementAge - currentAge;
        int monthsToRetirement = yearsToRetirement * 12;

        if (yearsToRetirement <= 0) {
            return new RetirementResult(0, 0,
                    java.util.Calendar.getInstance().get(java.util.Calendar.YEAR),
                    currentSavings, 0, "您已达到或超过退休年龄！");
        }

        double monthlySavings = monthlyIncome * (monthlySavingsPercent / 100.0);
        double monthlyRate = annualReturnRate / 100.0 / 12.0;

        double futureValueOfCurrentSavings = currentSavings *
                Math.pow(1 + annualReturnRate / 100.0, yearsToRetirement);

        double futureValueOfMonthlySavings;
        if (monthlyRate == 0) {
            futureValueOfMonthlySavings = monthlySavings * monthsToRetirement;
        } else {
            futureValueOfMonthlySavings = monthlySavings *
                    (Math.pow(1 + monthlyRate, monthsToRetirement) - 1) / monthlyRate;
        }

        double totalSavings = futureValueOfCurrentSavings + futureValueOfMonthlySavings;
        totalSavings = Math.round(totalSavings * 100.0) / 100.0;

        int retirementYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + yearsToRetirement;

        double monthlyPension = totalSavings * 0.04 / 12.0;
        monthlyPension = Math.round(monthlyPension * 100.0) / 100.0;

        String advice;
        if (monthlyPension >= monthlyIncome * 0.7) {
            advice = "您的退休规划非常充足，可以安心享受退休生活！";
        } else if (monthlyPension >= monthlyIncome * 0.5) {
            advice = "您的退休规划基本充足，建议适当增加储蓄比例。";
        } else {
            advice = "您的退休规划可能需要调整，建议提高储蓄比例或延迟退休年龄。";
        }

        return new RetirementResult(yearsToRetirement, monthsToRetirement,
                retirementYear, totalSavings, monthlyPension, advice);
    }
}
