package com.example.lab1_2_nguyenxuanhai;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AnimalSoundActivity extends AppCompatActivity {

    private LinearLayout layoutMain;
    private ImageView imgAnimal;
    private TextView txtTitle;

    //  Danh sách màu nền ngẫu nhiên
    private final int[] colors = {
            Color.parseColor("#F44336"), // đỏ
            Color.parseColor("#E91E63"), // hồng
            Color.parseColor("#9C27B0"), // tím
            Color.parseColor("#3F51B5"), // xanh dương
            Color.parseColor("#009688"), // xanh ngọc
            Color.parseColor("#FF9800"), // cam
            Color.parseColor("#4CAF50")  // xanh lá
    };

    // 2 icon sư tử
    private final int[] lionImages = {
            R.drawable.ic_lion,
            R.drawable.ic_lion_face
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_sound);

        // Ánh xạ các View
        layoutMain = findViewById(R.id.layoutMain);
        imgAnimal = findViewById(R.id.imgAnimal);
        txtTitle = findViewById(R.id.txtTitle);

        // Gọi hàm thay đổi ngẫu nhiên khi khởi động
        setRandomBackgroundAndIcon();
    }

    //  Hàm chọn ngẫu nhiên màu nền và icon sư tử
    private void setRandomBackgroundAndIcon() {
        Random random = new Random();

        // Màu nền ngẫu nhiên
        int randomColor = colors[random.nextInt(colors.length)];
        layoutMain.setBackgroundColor(randomColor);

        // Icon sư tử ngẫu nhiên
        int randomLion = lionImages[random.nextInt(lionImages.length)];
        imgAnimal.setImageResource(randomLion);

        // Cập nhật text hiển thị
        txtTitle.setText("Lion Sound");
    }
}
