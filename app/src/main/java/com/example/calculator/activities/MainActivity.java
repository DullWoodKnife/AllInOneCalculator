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
        modules.add(new CalculatorModule("电阻转换", "Ω", R.drawable.ic_resistance, ResistanceActivity.class));
        modules.add(new CalculatorModule("油耗量转换", "⛽", R.drawable.ic_fuel, FuelConsumptionActivity.class));
        modules.add(new CalculatorModule("能量转换", "⚡", R.drawable.ic_energy, EnergyActivity.class));
        modules.add(new CalculatorModule("年龄计算", "Age", R.drawable.ic_age, AgeActivity.class));
        modules.add(new CalculatorModule("BMI指数", "BMI", R.drawable.ic_bmi, BMIActivity.class));
        modules.add(new CalculatorModule("购车计算", "🚗", R.drawable.ic_car, CarActivity.class));
        modules.add(new CalculatorModule("退休计算", "☕", R.drawable.ic_retirement, RetirementActivity.class));
        modules.add(new CalculatorModule("亲戚称呼", "亲", R.drawable.ic_relationship, RelationshipActivity.class));
    }
}
