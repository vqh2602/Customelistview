package com.example.customelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import nguyenvanquan7826.com.Balan;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

     TextView tvMath;
     TextView tvResult;

     int[] idButton = {
            R.id.btn0,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnResult,
            R.id.btnPlus, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
            R.id.btnC, R.id.btnOpen, R.id.btnClose
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        connectView();
    }

    private void connectView() {
        tvMath = findViewById(R.id.tvMath);
        tvResult = findViewById(R.id.tvResult);
        Toast.makeText(CalculatorActivity.this,getString(R.string.name_hs) ,Toast.LENGTH_LONG).show();
        for (int j : idButton) {
            findViewById(j).setOnClickListener(this);
        }

        init();
    }

    private void init() {
        tvMath.setText("Nhập biểu thức");
        tvResult.setText("0");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
      //  Toast.makeText(CalculatorActivity.this,"done"+id,Toast.LENGTH_SHORT).show();
        // check button number and button operator
        for (int i = 0; i < idButton.length ; i++) {
            if (id == idButton[i]) {

                // clear screen
                if (id == R.id.btnC) {
                    init();
                    return;
                }

                // calculation
                if (id == R.id.btnResult) {
                    callBalan();
                    Toast.makeText(CalculatorActivity.this,"done",Toast.LENGTH_LONG).show();
                }

                String text = ((Button) findViewById(id)).getText().toString();

                // clear char | on top
                if (tvMath.getText().toString().trim().equals("Nhập biểu thức")) {
                    tvMath.setText("");
                }

                tvMath.append(text);
                return;
            }
        }


    }

    private void callBalan() {
        String math = tvMath.getText().toString().trim();
        if (math.length() > 0) {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();

            // check error
            if (error != null) {
                tvResult.setText(error);
            } else { // show result
                tvResult.setText(result);
            }
        }
    }
}