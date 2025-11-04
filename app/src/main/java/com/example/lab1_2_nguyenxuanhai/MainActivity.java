package com.example.lab1_2_nguyenxuanhai;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText edtSo1, edtSo2;
    TextView txtKetQua;
    Button btnCong, btnTru, btnNhan, btnChia, btnChiaDu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtSo1 = findViewById(R.id.edtSo1);
        edtSo2 = findViewById(R.id.edtSo2);
        txtKetQua = findViewById(R.id.txtKetQua);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        btnChiaDu = findViewById(R.id.btnChiaDu);

        // Khi thay đổi nội dung EditText thì xóa kết quả
        edtSo1.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtKetQua.setText("Kết quả:");
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        edtSo2.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtKetQua.setText("Kết quả:");
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });



        btnCong.setOnClickListener(v -> tinhToan("+"));
        btnTru.setOnClickListener(v -> tinhToan("-"));
        btnNhan.setOnClickListener(v -> tinhToan("*"));
        btnChia.setOnClickListener(v -> tinhToan("/"));
        btnChiaDu.setOnClickListener(v -> tinhToan("%"));
    }

    // Hàm xử lý phép toán
    private void tinhToan(String phep) {
        try {
            double so1 = Double.parseDouble(edtSo1.getText().toString());
            double so2 = Double.parseDouble(edtSo2.getText().toString());
            double kq = 0;

            switch (phep) {
                case "+": kq = so1 + so2; break;
                case "-": kq = so1 - so2; break;
                case "*": kq = so1 * so2; break;
                case "/":
                    if (so2 == 0) {
                        Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    kq = so1 / so2;
                    break;
                case "%":
                    kq = so1 % so2;
                    break;
            }

            txtKetQua.setText("Kết quả: " + kq);

        } catch (Exception e) {
            Toast.makeText(this, "Vui lòng nhập đủ hai số!", Toast.LENGTH_SHORT).show();
        }
    }
}
