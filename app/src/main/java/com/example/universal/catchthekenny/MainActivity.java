package com.example.universal.catchthekenny;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    View viewById;
    Runnable runnable;
    Handler handler;
    int sayac;
    TextView txtSayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageKenny);
        viewById = findViewById(R.id.layout);
        txtSayac = (TextView) findViewById(R.id.txtSayac);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayac ++;
                txtSayac.setText("Yakalanan Bahadır Sayısı:" + sayac);

            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                randomCoordinate();
                handler.postDelayed(runnable, 500);
            }
        };
            handler.post(runnable);


        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextView txtSure = (TextView) findViewById(R.id.txtSure);
                txtSure.setText("Süre :" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(), "Tebrikler " +sayac + " adet Bahadır yakaladınız. ", Toast.LENGTH_LONG).show();
                handler.removeCallbacks(runnable);

                Intent intent = getIntent();
                /**
                 * finish diyerek çalışan aktivity'yi sonlandırıyoruz. Start ile yeniden başşlatıyoruz.
                 */
                finish();
                startActivity(intent);
            }
        }.start();
    }

    private void randomCoordinate() {
        Random random = new Random();
        int x = random.nextInt(500);
        int y = random.nextInt(500);

        imageView.setLeft(x);
        imageView.setTop(y);
    }
}
