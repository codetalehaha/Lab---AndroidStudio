package com.example.lab_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nhapNamSinh;
    Button nutXemNamSinh;
    TextView ketQua;

    String currentInput = "";
    double num1 = 0, num2 = 0;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nhapNamSinh = findViewById(R.id.nhapNamSinh);
        nutXemNamSinh = findViewById(R.id.nutXemNamSinh);
        ketQua = findViewById(R.id.ketQua);

        nutXemNamSinh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String namSinhText = nhapNamSinh.getText().toString();
                int namSinh = Integer.parseInt(namSinhText);
                if (!namSinhText.isEmpty()) {
                    String ketQuaKiemTra = tinhThienCanDiaChi(namSinh);
                    ketQua.setText("Năm " + namSinh + ": " + ketQuaKiemTra);
                } else {
                    ketQua.setText("Vui lòng nhập năm sinh.");
                }
            }
        });
        setupCalculatorButtons();
    }

    private String tinhThienCanDiaChi(int namSinh) {
        String[] thienCan = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        String[] diaChi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi"};
        String[] thienCanDacTinh = {
                "Canh: Tính cách kiên định, thông minh",
                "Tân: Tinh tế, nhạy bén",
                "Nhâm: Tính cách lạc quan, hòa đồng",
                "Quý: Nhạy bén, cẩn thận",
                "Giáp: Dũng cảm, quyết đoán",
                "Ất: Hiền lành, chân thành",
                "Bính: Nhiệt huyết, mạnh mẽ",
                "Đinh: Độc lập, cẩn trọng",
                "Mậu: Trách nhiệm, kiên nhẫn",
                "Kỷ: Trung thực, thận trọng"
        };
        String[] diaChiDacTinh = {
                "Thân: Hoạt bát, thông minh",
                "Dậu: Tự tin, chăm chỉ",
                "Tuất: Chân thành, trung thực",
                "Hợi: Hòa đồng, hiền lành",
                "Tý: Lanh lợi, nhanh nhẹn",
                "Sửu: Kiên nhẫn, kiên định",
                "Dần: Dũng cảm, quyết liệt",
                "Mão: Mềm mại, khéo léo",
                "Thìn: Tự tin, quyến rũ",
                "Tỵ: Khôn ngoan, quyết đoán",
                "Ngọ: Độc lập, nhiệt huyết",
                "Mùi: Tinh tế, nhạy cảm"
        };

        String can = thienCan[namSinh % 10];
        String chi = diaChi[namSinh % 12];

        String dacTinhCan = thienCanDacTinh[namSinh % 10];
        String dacTinhChi = diaChiDacTinh[namSinh % 12];

        return can + " " + chi + "\nĐặc tính cơ bản:\n" + dacTinhCan + ", " + dacTinhChi;
    }

    private void setupCalculatorButtons() {
        int[] numberButtonIds = {
                R.id.phim0, R.id.phim1, R.id.phim2, R.id.phim3, R.id.phim4,
                R.id.phim5, R.id.phim6, R.id.phim7, R.id.phim8, R.id.phim9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::handleNumberButtonClick);
        }

        findViewById(R.id.phimCong).setOnClickListener(v -> handleOperatorButtonClick("+"));
        findViewById(R.id.phimTru).setOnClickListener(v -> handleOperatorButtonClick("-"));
        findViewById(R.id.phimNhan).setOnClickListener(v -> handleOperatorButtonClick("*"));
        findViewById(R.id.phimChia).setOnClickListener(v -> handleOperatorButtonClick("/"));
        findViewById(R.id.phimBang).setOnClickListener(v -> calculateResult());
        findViewById(R.id.nutDEL).setOnClickListener(v -> resetCalculator());
        findViewById(R.id.nutAC).setOnClickListener(v -> clearAll());
    }

    @SuppressLint("SetTextI18n")
    private void handleNumberButtonClick(View v) {
        Button button = (Button) v;
        currentInput += button.getText().toString();
        ketQua.setText(ketQua.getText().toString() + button.getText().toString());
    }

    @SuppressLint("SetTextI18n")
    private void handleOperatorButtonClick(String op) {
        if (!currentInput.isEmpty()) {
            num1 = Double.parseDouble(currentInput);
            operator = op;
            ketQua.setText(ketQua.getText().toString() + " " + op + " ");
            currentInput = "";
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            num2 = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        ketQua.setText("Lỗi chia cho 0");
                        return;
                    }
                    break;
            }
            // Hiển thị kết quả cuối cùng cùng với các bước
            ketQua.setText(ketQua.getText().toString() + " = " + result);
            currentInput = "";
            operator = "";
        }
    }

    private void resetCalculator() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            ketQua.setText(ketQua.getText().toString().substring(0, ketQua.getText().length() - 1));
        }
    }

    private void clearAll() {
        currentInput = "";
        operator = "";
        num1 = 0;
        num2 = 0;
        ketQua.setText("");
    }

}