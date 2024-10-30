package com.example.lab_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nhapSNT,nhapSoA, nhapSoB, nhapSoC;
    Button nutKiemTraSNT, nutKiemTra3CanhTamGiac, nutGiaiPTBac2;
    TextView ketQuaKiemTra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nhapSNT = findViewById(R.id.nhapSNT);
        nutKiemTraSNT = findViewById(R.id.nutKiemTraSNT);
        ketQuaKiemTra = findViewById(R.id.ketQuaKiemTra);
        nhapSoA = findViewById(R.id.nhapSoA);
        nhapSoB = findViewById(R.id.nhapSoB);
        nhapSoC = findViewById(R.id.nhapSoC);
        nutKiemTra3CanhTamGiac = findViewById(R.id.nutKiemTra3CanhTamGiac);
        nutGiaiPTBac2 = findViewById(R.id.nutGiaiPTBac2);

        nutKiemTraSNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPrimeNumber();
            }
        });

        nutKiemTra3CanhTamGiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkTriangle();
            }
        });

        nutGiaiPTBac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                solveQuadraticEquation();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkTriangle() {
        String aText = nhapSoA.getText().toString();
        String bText = nhapSoB.getText().toString();
        String cText = nhapSoC.getText().toString();

        if (aText.isEmpty() || bText.isEmpty() || cText.isEmpty()) {
            ketQuaKiemTra.setText("\tVui lòng nhập đủ ba cạnh.");
            return;
        }

        int a = Integer.parseInt(aText);
        int b = Integer.parseInt(bText);
        int c = Integer.parseInt(cText);

        if (a + b > c && a + c > b && b + c > a) {
            ketQuaKiemTra.setText("\tBa cạnh này tạo thành một tam giác.");
        } else {
            ketQuaKiemTra.setText("\tBa cạnh này không tạo thành một tam giác.");
        }
    }

    @SuppressLint("SetTextI18n")
    private void solveQuadraticEquation() {
        String aText = nhapSoA.getText().toString();
        String bText = nhapSoB.getText().toString();
        String cText = nhapSoC.getText().toString();

        if (aText.isEmpty() || bText.isEmpty() || cText.isEmpty()) {
            ketQuaKiemTra.setText("\tVui lòng nhập đầy đủ hệ số.");
            return;
        }

        double a = Double.parseDouble(aText);
        double b = Double.parseDouble(bText);
        double c = Double.parseDouble(cText);

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            ketQuaKiemTra.setText("\tPhương trình có hai nghiệm:\n x1 = " + x1 + "\n x2 = " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            ketQuaKiemTra.setText("\tPhương trình có nghiệm kép: x = " + x);
        } else {
            ketQuaKiemTra.setText("\tPhương trình vô nghiệm.");
        }
    }
    @SuppressLint("SetTextI18n")
    private void checkPrimeNumber() {
        String input = nhapSNT.getText().toString();

        if (input.isEmpty()) {
            ketQuaKiemTra.setText("\tVui lòng nhập một số.");
            return;
        }

        int number = Integer.parseInt(input);
        if (isPrime(number)) {
            ketQuaKiemTra.setText("\t" + number + " là số nguyên tố.");
        } else {
            ketQuaKiemTra.setText("\t" + number + " không phải là số nguyên tố.");
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
