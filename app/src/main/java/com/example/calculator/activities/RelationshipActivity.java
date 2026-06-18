package com.example.calculator.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.calculator.R;
import com.example.calculator.utils.RelationshipCalculator;
import java.util.ArrayList;
import java.util.List;

public class RelationshipActivity extends AppCompatActivity {

    private TextView tvRelationPath;
    private TextView tvResult;
    private Button btnBackspace;
    private Button btnClear;
    private Button btnMutualCheck;
    private Button btnEqual;

    private List<String> relationChain = new ArrayList<>();
    private boolean isMutualCheckMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("亲戚称呼");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        setupRelationButtons();
    }

    private void initViews() {
        tvRelationPath = findViewById(R.id.tvRelationPath);
        tvResult = findViewById(R.id.tvResult);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnClear = findViewById(R.id.btnClear);
        btnMutualCheck = findViewById(R.id.btnMutualCheck);
        btnEqual = findViewById(R.id.btnEqual);

        btnBackspace.setOnClickListener(v -> onBackspace());
        btnClear.setOnClickListener(v -> onClear());
        btnMutualCheck.setOnClickListener(v -> onMutualCheck());
        btnEqual.setOnClickListener(v -> onCalculate());
    }

    private void setupRelationButtons() {
        // 设置关系按钮点击事件
        setupButton(R.id.btnHusband, "夫");
        setupButton(R.id.btnWife, "妻");
        setupButton(R.id.btnFather, "父");
        setupButton(R.id.btnMother, "母");
        setupButton(R.id.btnElderBrother, "兄");
        setupButton(R.id.btnYoungerBrother, "弟");
        setupButton(R.id.btnElderSister, "姐");
        setupButton(R.id.btnYoungerSister, "妹");
        setupButton(R.id.btnSon, "子");
        setupButton(R.id.btnDaughter, "女");
    }

    private void setupButton(int buttonId, String relation) {
        Button btn = findViewById(buttonId);
        if (btn != null) {
            btn.setOnClickListener(v -> onRelationButtonClick(relation));
        }
    }

    private void onRelationButtonClick(String relation) {
        relationChain.add(relation);
        updateDisplay();
    }

    private void onBackspace() {
        if (!relationChain.isEmpty()) {
            relationChain.remove(relationChain.size() - 1);
            updateDisplay();
        }
    }

    private void onClear() {
        relationChain.clear();
        tvRelationPath.setText("");
        tvResult.setText("");
    }

    private void onMutualCheck() {
        isMutualCheckMode = !isMutualCheckMode;
        btnMutualCheck.setText(isMutualCheckMode ? "互查(开)" : "互查");
    }

    private void onCalculate() {
        if (relationChain.isEmpty()) {
            tvResult.setText("请先选择关系");
            return;
        }

        String result = RelationshipCalculator.calculateRelationship(relationChain);
        tvResult.setText(result);
    }

    private void updateDisplay() {
        if (relationChain.isEmpty()) {
            tvRelationPath.setText("");
            tvResult.setText("");
            return;
        }

        // 构建关系路径显示
        StringBuilder path = new StringBuilder("我的");
        for (int i = 0; i < relationChain.size(); i++) {
            String relation = relationChain.get(i);
            String displayName = getRelationDisplayName(relation);
            if (i > 0) {
                path.append("的");
            }
            path.append(displayName);
        }
        tvRelationPath.setText(path.toString());

        // 实时计算结果
        String result = RelationshipCalculator.getSimpleResult(relationChain);
        tvResult.setText(result);
    }

    private String getRelationDisplayName(String relation) {
        switch (relation) {
            case "父": return "爸爸";
            case "母": return "妈妈";
            case "兄": return "哥哥";
            case "弟": return "弟弟";
            case "姐": return "姐姐";
            case "妹": return "妹妹";
            case "夫": return "丈夫";
            case "妻": return "妻子";
            case "子": return "儿子";
            case "女": return "女儿";
            default: return relation;
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
