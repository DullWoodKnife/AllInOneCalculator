package com.example.calculator.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calculator.R;
import com.example.calculator.adapters.ModuleAdapter;
import com.example.calculator.models.CalculatorModule;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModuleAdapter adapter;
    private List<CalculatorModule> modules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        initModules();
        adapter = new ModuleAdapter(this, modules);
        recyclerView.setAdapter(adapter);
    }

    private void initModules() {
        modules = new ArrayList<>();
        modules.add(new CalculatorModule("电阻转换", "Ω", 0, ResistanceActivity.class));
        modules.add(new CalculatorModule("油耗量转换", "L", 0, FuelConsumptionActivity.class));
        modules.add(new CalculatorModule("能量转换", "E", 0, EnergyActivity.class));
        modules.add(new CalculatorModule("年龄计算", "Age", 0, AgeActivity.class));
        modules.add(new CalculatorModule("BMI指数", "BMI", 0, BMIActivity.class));
        modules.add(new CalculatorModule("购车计算", "Car", 0, CarActivity.class));
        modules.add(new CalculatorModule("退休计算", "Ret", 0, RetirementActivity.class));
        modules.add(new CalculatorModule("亲戚称呼", "亲", 0, RelationshipActivity.class));
    }
}
