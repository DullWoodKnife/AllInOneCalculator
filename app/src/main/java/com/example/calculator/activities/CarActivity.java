package com.example.calculator.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.utils.CarCalculator;

public class CarActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbLoan, rbFuel;
    private EditText etCarPrice, etDownPayment, etLoanYears, etInterestRate;
    private EditText etDistance, etFuelConsumption, etFuelPrice;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("购车计算");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup = findViewById(R.id.radioGroup);
        rbLoan = findViewById(R.id.rbLoan);
        rbFuel = findViewById(R.id.rbFuel);
        etCarPrice = findViewById(R.id.etCarPrice);
        etDownPayment = findViewById(R.id.etDownPayment);
        etLoanYears = findViewById(R.id.etLoanYears);
        etInterestRate = findViewById(R.id.etInterestRate);
        etDistance = findViewById(R.id.etDistance);
        etFuelConsumption = findViewById(R.id.etFuelConsumption);
        etFuelPrice = findViewById(R.id.etFuelPrice);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> updateUI());
        btnCalculate.setOnClickListener(v -> calculate());
        updateUI();
    }

    private void updateUI() {
        boolean isLoan = rbLoan.isChecked();
        etCarPrice.setEnabled(isLoan);
        etDownPayment.setEnabled(isLoan);
        etLoanYears.setEnabled(isLoan);
        etInterestRate.setEnabled(isLoan);
        etDistance.setEnabled(!isLoan);
        etFuelConsumption.setEnabled(!isLoan);
        etFuelPrice.setEnabled(!isLoan);
    }

    private void calculate() {
        if (rbLoan.isChecked()) {
            calculateLoan();
        } else {
            calculateFuel();
        }
    }

    private void calculateLoan() {
        String priceStr = etCarPrice.getText().toString().trim();
        String downStr = etDownPayment.getText().toString().trim();
        String yearsStr = etLoanYears.getText().toString().trim();
        String rateStr = etInterestRate.getText().toString().trim();

        if (priceStr.isEmpty() || downStr.isEmpty() || yearsStr.isEmpty() || rateStr.isEmpty()) {
            tvResult.setText("请填写所有字段");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            double downPercent = Double.parseDouble(downStr);
            int years = Integer.parseInt(yearsStr);
            double rate = Double.parseDouble(rateStr);

            CarCalculator.CarLoanResult result = CarCalculator.calculateLoan(price, downPercent, years, rate);

            StringBuilder sb = new StringBuilder();
            sb.append("购车贷款计算结果：\n\n");
            sb.append("车辆价格：").append(String.format("%.2f", price)).append(" 元\n");
            sb.append("首付金额：").append(String.format("%.2f", result.downPayment)).append(" 元 (").append(downPercent).append("%)\n");
            sb.append("贷款金额：").append(String.format("%.2f", result.loanAmount)).append(" 元\n\n");
            sb.append("月供：").append(String.format("%.2f", result.monthlyPayment)).append(" 元\n");
            sb.append("还款总额：").append(String.format("%.2f", result.totalPayment)).append(" 元\n");
            sb.append("支付利息：").append(String.format("%.2f", result.totalInterest)).append(" 元");

            tvResult.setText(sb.toString());
        } catch (NumberFormatException e) {
            tvResult.setText("请输入有效的数字");
        }
    }

    private void calculateFuel() {
        String distanceStr = etDistance.getText().toString().trim();
        String consumptionStr = etFuelConsumption.getText().toString().trim();
        String priceStr = etFuelPrice.getText().toString().trim();

        if (distanceStr.isEmpty() || consumptionStr.isEmpty() || priceStr.isEmpty()) {
            tvResult.setText("请填写所有字段");
            return;
        }

        try {
            double distance = Double.parseDouble(distanceStr);
            double consumption = Double.parseDouble(consumptionStr);
            double price = Double.parseDouble(priceStr);

            double fuelCost = CarCalculator.calculateFuelCost(distance, consumption, price);
            double fuelNeeded = distance * consumption / 100.0;

            StringBuilder sb = new StringBuilder();
            sb.append("燃油费用计算结果：\n\n");
            sb.append("行驶里程：").append(String.format("%.1f", distance)).append(" 公里\n");
            sb.append("百公里油耗：").append(String.format("%.1f", consumption)).append(" L/100km\n");
            sb.append("燃油单价：").append(String.format("%.2f", price)).append(" 元/L\n\n");
            sb.append("需燃油量：").append(String.format("%.2f", fuelNeeded)).append(" L\n");
            sb.append("燃油费用：").append(String.format("%.2f", fuelCost)).append(" 元");

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
