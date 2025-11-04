package com.example.lab1_2_nguyenxuanhai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RandomDiceActivity extends AppCompatActivity {

    private TextView txtRandomNumber, txtDice, txtDiceNumber;
    private Button btnGenerate, btnRollDice;
    private Random random;

    // Mảng chứa các ký tự unicode của xúc xắc
    private final String[] diceFaces = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_dice);

        // Ánh xạ các thành phần từ XML
        initViews();

        // Khởi tạo đối tượng Random
        random = new Random();

        // Thiết lập sự kiện cho các nút
        setupEventListeners();
    }

    private void initViews() {
        txtRandomNumber = findViewById(R.id.txtRandomNumber);
        txtDice = findViewById(R.id.txtDice);
        txtDiceNumber = findViewById(R.id.txtDiceNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnRollDice = findViewById(R.id.btnRollDice);
    }

    private void setupEventListeners() {
        // Sự kiện cho nút tạo số ngẫu nhiên
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();
            }
        });

        // Sự kiện cho nút tung xúc xắc
        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private void generateRandomNumber() {
        // Tạo số ngẫu nhiên từ 1-100
        int randomNumber = random.nextInt(100) + 1;

        // Hiển thị số lên TextView
        txtRandomNumber.setText(String.valueOf(randomNumber));

        // Thêm hiệu ứng
        animateText(txtRandomNumber);
    }

    private void rollDice() {
        // Tạo số ngẫu nhiên từ 1-6
        int diceNumber = random.nextInt(6) + 1;

        // Hiển thị mặt xúc xắc tương ứng
        txtDice.setText(diceFaces[diceNumber - 1]);

        // Hiển thị số
        txtDiceNumber.setText("Số: " + diceNumber);

        // Thêm hiệu ứng
        animateDice();
        animateText(txtDiceNumber);
    }

    private void animateDice() {
        // Tạo hiệu ứng xoay cho xúc xắc
        txtDice.animate()
                .rotationBy(360)
                .setDuration(500)
                .start();
    }

    private void animateText(TextView textView) {
        // Hiệu ứng phóng to nhỏ cho text
        textView.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(200)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        textView.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setDuration(200)
                                .start();
                    }
                })
                .start();
    }
}