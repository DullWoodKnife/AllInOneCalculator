package com.example.calculator.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.utils.RetirementCalculator;

public class RetirementActivity extends AppCompatActivity {

    private EditText etCurrentAge, etRetirementAge, etMonthlyIncome;
    private EditText etSavingsPercent, etCurrentSavings, etReturnRate;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirement);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("退休计算");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCurrentAge = findViewById(R.id.etCurrentAge);
        etRetirementAge = findViewById(R.id.etRetirementAge);
        etMonthlyIncome = findViewById(R.id.etMonthlyIncome);
        etSavingsPercent = findViewById(R.id.etSavingsPercent);
        etCurrentSavings = findViewById(R.id.etCurrentSavings);
        etReturnRate = findViewById(R.id.etReturnRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> calculateRetirement());
    }

    private void calculateRetirement() {
        String currentAgeStr = etCurrentAge.getText().toString().trim();
        String retirementAgeStr = etRetirementAge.getText().toString().trim();
        String incomeStr = etMonthlyIncome.getText().toString().trim();
        String savingsPercentStr = etSavingsPercent.getText().toString().trim();
        String currentSavingsStr = etCurrentSavings.getText().toString().trim();
        String returnRateStr = etReturnRate.getText().toString().trim();

        if (currentAgeStr.isEmpty() || retirementAgeStr.isEmpty() || incomeStr.isEmpty() ||
                savingsPercentStr.isEmpty() || currentSavingsStr.isEmpty() || returnRateStr.isEmpty()) {
            tvResult.setText("请填写所有字段");
            return;
        }

        try {
            int currentAge = Integer.parseInt(currentAgeStr);
            int retirementAge = Integer.parseInt(retirementAgeStr);
            double monthlyIncome = Double.parseDouble(incomeStr);
            double savingsPercent = Double.parseDouble(savingsPercentStr);
            double currentSavings = Double.parseDouble(currentSavingsStr);
            double returnRate = Double.parseDouble(returnRateStr);

            if (currentAge >= retirementAge) {
                tvResult.setText("退休年龄必须大于当前年龄");
                return;
            }

            RetirementCalculator.RetirementResult result =
                    RetirementCalculator.calculateRetirement(currentAge, retirementAge, monthlyIncome,
                            savingsPercent, currentSavings, returnRate);

            StringBuilder sb = new StringBuilder();
            sb.append("退休规划计算结果：\n\n");
            sb.append("距离退休：").append(result.yearsToRetirement).append(" 年 (").append(result.monthsToRetirement).append(" 个月)\n");
            sb.append("退休年份：").append(result.retirementYear).append(" 年\n\n");
            sb.append("预计退休总储蓄：").append(String.format("%.2f", result.totalSavings)).append(" 元\n");
            sb.append("预计月养老金：").append(String.format("%.2f", result.monthlyPension)).append(" 元\n\n");
            sb.append("建议：\n").append(result.advice);

            tvResult.setText(sb.toString());
        } catch (NumberFormatException e) {
            tvResult.setText("请输入有效的数字");
        }
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
