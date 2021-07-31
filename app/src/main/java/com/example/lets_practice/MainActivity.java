package com.example.lets_practice;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answer = new ArrayList<>();
    int answerLocation;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    /* renamed from: c */
    int f39c = 0;

    /* renamed from: d */
    int f40d = 0;
    boolean restart = true;
    TextView start;
    TextView text;
    TextView text2;
    TextView text3;
    TextView text4;

    public void getQuestion() {
        Random random = new Random();
        this.answerLocation = random.nextInt(4);
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        TextView textView = this.text2;
        textView.setText(String.valueOf(a) + "+" + String.valueOf(b));
        for (int i = 0; i < 4; i++) {
            if (i == this.answerLocation) {
                this.answer.add(Integer.valueOf(a + b));
            } else {
                int incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(41);
                }
                this.answer.add(Integer.valueOf(incorrectAnswer));
            }
        }
        this.button1.setText(String.valueOf(this.answer.get(0)));
        this.button2.setText(String.valueOf(this.answer.get(1)));
        this.button3.setText(String.valueOf(this.answer.get(2)));
        this.button4.setText(String.valueOf(this.answer.get(3)));
        this.answer.clear();
    }

    public void restart(View view) {
        this.button5.setEnabled(false);
        this.f39c = 0;
        this.f40d = 0;
        this.text.setText("30s");
        this.text3.setText("0/0");
        this.restart = true;
        this.text4.setVisibility(View.VISIBLE);
        this.text2.setVisibility(View.INVISIBLE);
        getQuestion();
        new CountDownTimer(5100, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView textView = MainActivity.this.text;
                textView.setText(String.valueOf((int) (millisUntilFinished / 1000)) + "s");
            }

            public void onFinish() {
                MainActivity.this.restart = false;
                MainActivity.this.text2.setVisibility(View.VISIBLE);
                TextView textView = MainActivity.this.text4;
                textView.setText("ADDITION OVER! yr score is " + String.valueOf(MainActivity.this.f39c) + "/" + String.valueOf(MainActivity.this.f40d));
                MainActivity.this.button5.setEnabled(true);
                MainActivity.this.button6.setEnabled(true);
            }
        }.start();
    }

    public void start(View view) {
        this.start.setVisibility(View.VISIBLE);
        getQuestion();
        restart(findViewById(R.id.button));
    }

    public void push(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void press(View view) {
        if (this.restart) {
            if (String.valueOf(view.getTag()).equals(String.valueOf(this.answerLocation))) {
                this.f39c++;
                this.text4.setText("CORRECT!");
            } else {
                this.text4.setText("WRONG!");
            }
            this.f40d++;
            this.text4.setVisibility(View.INVISIBLE);
            this.text3.setText(String.valueOf(this.f39c) + "/" + String.valueOf(this.f40d));
            getQuestion();
            return;
        }
        Toast.makeText(getApplicationContext(), "press try again to play", Toast.LENGTH_LONG).show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        setContentView((int) R.layout.activity_main);
        this.text4 = (TextView) findViewById(R.id.textView4);
        this.text2 = (TextView) findViewById(R.id.textView2);
        this.text3 = (TextView) findViewById(R.id.textView3);
         this.text = (TextView) findViewById(R.id.textView);
        this.button1 = (Button) findViewById(R.id.button1);
        this.button2 = (Button) findViewById(R.id.button2);
        this.button3 = (Button) findViewById(R.id.button3);
        this.button4 = (Button) findViewById(R.id.button4);
        this.button5 = (Button) findViewById(R.id.button);
        this.button6 = (Button) findViewById(R.id.button6);
        getQuestion();
        restart(findViewById(R.id.button));
        this.button5.setEnabled(false);
        this.button6.setEnabled(false);
        this.text4.setVisibility(View.VISIBLE);
    }
}
