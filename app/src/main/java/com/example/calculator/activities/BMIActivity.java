package com.example.calculator.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.utils.BMICalculator;

public class BMIActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BMI指数");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            tvResult.setText("请输入身高和体重");
            return;
        }

        try {
            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height <= 0 || weight <= 0) {
                tvResult.setText("请输入有效的正数");
                return;
            }

            BMICalculator.BMIResult result = BMICalculator.calculateBMI(height, weight);

            StringBuilder sb = new StringBuilder();
            sb.append("您的BMI指数：").append(result.bmi).append("\n\n");
            sb.append("体型分类：").append(result.category).append("\n\n");
            sb.append("健康建议：\n").append(result.healthAdvice).append("\n\n");
            sb.append("理想体重范围：\n");
            sb.append("  最低：").append(result.idealWeightMin).append(" kg\n");
            sb.append("  最高：").append(result.idealWeightMax).append(" kg");

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
