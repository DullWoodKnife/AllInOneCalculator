package com.example.calculator.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calculator.R;
import com.example.calculator.models.CalculatorModule;
import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context context;
    private List<CalculatorModule> modules;

    public ModuleAdapter(Context context, List<CalculatorModule> modules) {
        this.context = context;
        this.modules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalculatorModule module = modules.get(position);
        holder.tvIcon.setText(module.getIconText());
        holder.tvName.setText(module.getName());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, module.getTargetActivity());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvIcon;
        TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvIcon = itemView.findViewById(R.id.tvIcon);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
