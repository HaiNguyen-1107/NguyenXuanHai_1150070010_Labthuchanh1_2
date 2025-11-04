package com.example.lab1_2_nguyenxuanhai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Gán sự kiện cho icon gọi điện
        ImageView iconCall = findViewById(R.id.iconCall);

        iconCall.setOnClickListener(v -> {
            // Số điện thoại muốn gọi
            String phoneNumber = "0362060879";

            // Tạo Intent mở trình gọi
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));

            // Chạy Intent
            startActivity(intent);
        });
    }
}
