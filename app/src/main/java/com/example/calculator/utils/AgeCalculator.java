package com.example.calculator.utils;

import java.util.Calendar;

public class AgeCalculator {

    public static class AgeResult {
        public int years;
        public int months;
        public int days;
        public long totalDays;
        public long totalHours;
        public long totalMinutes;
        public long totalSeconds;

        public AgeResult(int years, int months, int days, long totalDays) {
            this.years = years;
            this.months = months;
            this.days = days;
            this.totalDays = totalDays;
            this.totalHours = totalDays * 24;
            this.totalMinutes = totalHours * 60;
            this.totalSeconds = totalMinutes * 60;
        }
    }

    public static AgeResult calculateAge(int birthYear, int birthMonth, int birthDay) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(birthYear, birthMonth - 1, birthDay);

        Calendar today = Calendar.getInstance();

        long diffMillis = today.getTimeInMillis() - birthDate.getTimeInMillis();
        long totalDays = diffMillis / (24 * 60 * 60 * 1000);

        int years = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        int months = today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH);
        int days = today.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH);

        if (days < 0) {
            months--;
            Calendar tempCal = Calendar.getInstance();
            tempCal.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1);
            days += tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        if (months < 0) {
            years--;
            months += 12;
        }

        return new AgeResult(years, months, days, totalDays);
    }

    public static int calculateZodiac(int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return 0;
        }
        int[] zodiacDays = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
        if (day < zodiacDays[month - 1]) {
            return (month - 1 + 12) % 12;
        } else {
            return month % 12;
        }
    }

    public static String getZodiacName(int zodiacIndex) {
        String[] zodiacs = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
                "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        return zodiacs[zodiacIndex];
    }

    public static String getChineseZodiac(int year) {
        String[] animals = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
        return animals[year % 12];
    }
}
