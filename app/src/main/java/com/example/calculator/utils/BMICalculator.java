package com.example.calculator.utils;

public class BMICalculator {

    public static class BMIResult {
        public double bmi;
        public String category;
        public String healthAdvice;
        public double idealWeightMin;
        public double idealWeightMax;

        public BMIResult(double bmi, String category, String healthAdvice,
                         double idealWeightMin, double idealWeightMax) {
            this.bmi = bmi;
            this.category = category;
            this.healthAdvice = healthAdvice;
            this.idealWeightMin = idealWeightMin;
            this.idealWeightMax = idealWeightMax;
        }
    }

    public static BMIResult calculateBMI(double heightCm, double weightKg) {
        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        bmi = Math.round(bmi * 10.0) / 10.0;

        String category;
        String advice;

        if (bmi < 18.5) {
            category = "偏瘦";
            advice = "建议适当增加营养摄入，进行适量力量训练以增加肌肉量。";
        } else if (bmi < 24.0) {
            category = "正常";
            advice = "保持良好的饮食和运动习惯，定期体检。";
        } else if (bmi < 28.0) {
            category = "偏胖";
            advice = "建议控制饮食热量，增加有氧运动，保持健康生活方式。";
        } else {
            category = "肥胖";
            advice = "建议咨询专业医生或营养师，制定科学的减重计划。";
        }

        double idealWeightMin = Math.round(18.5 * heightM * heightM * 10.0) / 10.0;
        double idealWeightMax = Math.round(24.0 * heightM * heightM * 10.0) / 10.0;

        return new BMIResult(bmi, category, advice, idealWeightMin, idealWeightMax);
    }
}
