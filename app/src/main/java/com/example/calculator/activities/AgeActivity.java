package com.example.calculator.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.utils.AgeCalculator;
import java.util.Calendar;

public class AgeActivity extends AppCompatActivity {

    private TextView tvBirthDate;
    private Button btnSelectDate;
    private Button btnCalculate;
    private TextView tvResult;

    private int birthYear, birthMonth, birthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("年龄计算");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvBirthDate = findViewById(R.id.tvBirthDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        Calendar today = Calendar.getInstance();
        birthYear = today.get(Calendar.YEAR) - 20;
        birthMonth = today.get(Calendar.MONTH) + 1;
        birthDay = today.get(Calendar.DAY_OF_MONTH);
        updateBirthDateDisplay();

        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnCalculate.setOnClickListener(v -> calculateAge());
    }

    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    birthYear = year;
                    birthMonth = month + 1;
                    birthDay = dayOfMonth;
                    updateBirthDateDisplay();
                }, birthYear, birthMonth - 1, birthDay);
        dialog.show();
    }

    private void updateBirthDateDisplay() {
        tvBirthDate.setText(String.format("%d年%02d月%02d日", birthYear, birthMonth, birthDay));
    }

    private void calculateAge() {
        AgeCalculator.AgeResult result = AgeCalculator.calculateAge(birthYear, birthMonth, birthDay);
        int zodiacIndex = AgeCalculator.calculateZodiac(birthMonth, birthDay);
        String zodiac = AgeCalculator.getZodiacName(zodiacIndex);
        String chineseZodiac = AgeCalculator.getChineseZodiac(birthYear);

        StringBuilder sb = new StringBuilder();
        sb.append("您的年龄：").append(result.years).append("岁 ")
                .append(result.months).append("个月 ")
                .append(result.days).append("天\n\n");
        sb.append("总计：\n");
        sb.append("  ").append(result.totalDays).append(" 天\n");
        sb.append("  ").append(result.totalHours).append(" 小时\n");
        sb.append("  ").append(result.totalMinutes).append(" 分钟\n");
        sb.append("  ").append(result.totalSeconds).append(" 秒\n\n");
        sb.append("星座：").append(zodiac).append("\n");
        sb.append("生肖：").append(chineseZodiac);

        tvResult.setText(sb.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
