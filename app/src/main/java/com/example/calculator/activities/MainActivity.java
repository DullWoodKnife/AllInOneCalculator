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
        modules.add(new CalculatorModule("电阻转换", "Ω", android.R.drawable.ic_menu_sort_by_size, ResistanceActivity.class));
        modules.add(new CalculatorModule("油耗量转换", "⛽", android.R.drawable.ic_menu_sort_by_size, FuelConsumptionActivity.class));
        modules.add(new CalculatorModule("能量转换", "⚡", android.R.drawable.ic_menu_sort_by_size, EnergyActivity.class));
        modules.add(new CalculatorModule("年龄计算", "Age", android.R.drawable.ic_menu_my_calendar, AgeActivity.class));
        modules.add(new CalculatorModule("BMI指数", "BMI", android.R.drawable.ic_menu_info_details, BMIActivity.class));
        modules.add(new CalculatorModule("购车计算", "🚗", android.R.drawable.ic_menu_agenda, CarActivity.class));
        modules.add(new CalculatorModule("退休计算", "☕", android.R.drawable.ic_menu_day, RetirementActivity.class));
        modules.add(new CalculatorModule("亲戚称呼", "亲", android.R.drawable.ic_menu_share, RelationshipActivity.class));
    }
}
