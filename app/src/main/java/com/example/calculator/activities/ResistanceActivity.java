package com.example.calculator.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.models.ConversionUnit;
import com.example.calculator.utils.ConversionUtils;
import java.util.List;

public class ResistanceActivity extends AppCompatActivity {

    private EditText etInput;
    private Spinner spinnerFrom, spinnerTo;
    private Button btnConvert;
    private TextView tvResult;

    private List<ConversionUnit> units;
    private int fromIndex = 0;
    private int toIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("电阻转换");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etInput = findViewById(R.id.etInput);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        units = ConversionUtils.getResistanceUnits();
        setupSpinners();

        btnConvert.setOnClickListener(v -> performConversion());
    }

    private void setupSpinners() {
        String[] unitNames = new String[units.size()];
        for (int i = 0; i < units.size(); i++) {
            unitNames[i] = units.get(i).getName() + " (" + units.get(i).getSymbol() + ")";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerFrom.setSelection(0);
        spinnerTo.setSelection(1);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromIndex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toIndex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void performConversion() {
        String inputStr = etInput.getText().toString().trim();
        if (inputStr.isEmpty()) {
            tvResult.setText("请输入数值");
            return;
        }

        try {
            double input = Double.parseDouble(inputStr);
            ConversionUnit from = units.get(fromIndex);
            ConversionUnit to = units.get(toIndex);
            double result = ConversionUtils.convert(input, from, to);

            tvResult.setText(String.format("%.6f %s = %.6f %s",
                    input, from.getSymbol(), result, to.getSymbol()));
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
