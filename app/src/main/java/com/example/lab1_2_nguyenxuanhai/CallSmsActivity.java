package com.example.lab1_2_nguyenxuanhai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallSmsActivity extends AppCompatActivity {

    private EditText edtPhoneNumber, edtMessage;
    private Button btnCall, btnSendSMS;

    // Request code cho permission
    private static final int PERMISSION_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms);

        // Ánh xạ các thành phần từ XML
        initViews();

        // Kiểm tra và yêu cầu quyền gọi điện (không cần quyền SMS)
        checkAndRequestPermissions();

        // Thiết lập sự kiện
        setupEventListeners();
    }

    private void initViews() {
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtMessage = findViewById(R.id.edtMessage);
        btnCall = findViewById(R.id.btnCall);
        btnSendSMS = findViewById(R.id.btnSendSMS);
    }

    private void setupEventListeners() {
        // Sự kiện nút Gọi điện
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        // Sự kiện nút Gửi tin nhắn
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSmsApp();
            }
        });
    }

    // === PHƯƠNG THỨC KIỂM TRA VÀ YÊU CẦU QUYỀN GỌI ĐIỆN ===
    private void checkAndRequestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_REQUEST_CODE
            );
        }
    }

    // === XỬ LÝ KẾT QUẢ YÊU CẦU QUYỀN ===
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã được cấp quyền gọi điện!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Chưa được cấp quyền gọi điện!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // === PHƯƠNG THỨC GỌI ĐIỆN ===
    private void makePhoneCall() {
        String phoneNumber = edtPhoneNumber.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);

        } else {
            Toast.makeText(this, "Chưa có quyền gọi điện!", Toast.LENGTH_LONG).show();
            checkAndRequestPermissions();
        }
    }

    // === PHƯƠNG THỨC MỞ ỨNG DỤNG TIN NHẮN ===
    private void openSmsApp() {
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String message = edtMessage.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (message.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập nội dung tin nhắn!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mở ứng dụng tin nhắn, điền sẵn nội dung
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber)); // Chỉ định ứng dụng nhắn tin
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
