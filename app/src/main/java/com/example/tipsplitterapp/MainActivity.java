package com.example.tipsplitterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    TextInputEditText tietBill;
    TextInputEditText tietNrPers;

    Button btnTip5;
    Button btnTip10;
    Button btnTip15;
    Button btnTip20;
    Button btnTip25;
    Button btnTip30;

    Button btnCalculate;

    TextView tvTipAmount;
    TextView tvTipAmountPers;
    TextView tvTotal;
    TextView tvTotalPers;

    double tipAmount;
    double tipAmountPers;
    double total;
    double totalPers;

    Button btnPressed;

    private static final String TIP_PERCENTAGE_5 = "5%";
    private static final String TIP_PERCENTAGE_10 = "10%";
    private static final String TIP_PERCENTAGE_15 = "15%";
    private static final String TIP_PERCENTAGE_20 = "20%";
    private static final String TIP_PERCENTAGE_25 = "25%";
    private static final String TIP_PERCENTAGE_30 = "30%";

    private double bill;
    private double nrPers;
    private int tipPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setListners();
    }

    private void setListners() {
        btnTip5.setOnClickListener(setTipPercentage());
        btnTip10.setOnClickListener(setTipPercentage());
        btnTip15.setOnClickListener(setTipPercentage());
        btnTip20.setOnClickListener(setTipPercentage());
        btnTip25.setOnClickListener(setTipPercentage());
        btnTip30.setOnClickListener(setTipPercentage());
        btnCalculate.setOnClickListener(setAmounts());
    }

    private View.OnClickListener setAmounts() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    setResults();
                    //nrPers = Integer.parseInt(tietNrPers.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Set bill amount, number of persons and tip percentage!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void setResults() {
        bill = Double.parseDouble(tietBill.getText().toString());
        nrPers = Integer.parseInt(tietNrPers.getText().toString());

        tipAmount = (bill*tipPercentage)/100;
        tipAmountPers = tipAmount/nrPers;
        total = bill + tipAmount;
        totalPers = total / nrPers;

        tvTipAmount.setText(getString(R.string.tip_amount_template, String.valueOf(tipAmount)));
        tvTipAmountPers.setText(getString(R.string.tip_amount_person_template, String.valueOf(tipAmountPers)));
        tvTotal.setText(getString(R.string.total_template, String.valueOf(total)));
        tvTotalPers.setText(getString(R.string.total_person_template, String.valueOf(totalPers)));
    }


    private boolean isValid() {
        if (tietBill.getText().toString().trim().isEmpty() || tietBill.getText() == null){
            return false;
        } else if (tietNrPers.toString().trim().isEmpty() || tietNrPers.getText() == null){
            return false;
        } else if (btnPressed == null){
            return false;
        } else return true;
    }

    private View.OnClickListener setTipPercentage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInitialColorButtons();
                view.setBackgroundColor(getResources().getColor(R.color.til_color));
                btnPressed = (Button) view;
                String btnPressedText = btnPressed.getText().toString();

                switch (btnPressedText){
                    case TIP_PERCENTAGE_5:
                        tipPercentage = 5;
                        break;
                    case TIP_PERCENTAGE_10:
                        tipPercentage = 10;
                        break;
                    case TIP_PERCENTAGE_15:
                        tipPercentage = 15;
                        break;
                    case TIP_PERCENTAGE_20:
                        tipPercentage = 20;
                        break;
                    case TIP_PERCENTAGE_25:
                        tipPercentage = 25;
                        break;
                    case TIP_PERCENTAGE_30:
                        tipPercentage = 30;
                        break;
                }
                //Log.i("test", String.valueOf(tipPercentage));
            }
        };
    }

    private void setInitialColorButtons() {
        btnTip5.setBackgroundColor(getResources().getColor(R.color.btn_color));
        btnTip10.setBackgroundColor(getResources().getColor(R.color.btn_color));
        btnTip15.setBackgroundColor(getResources().getColor(R.color.btn_color));
        btnTip20.setBackgroundColor(getResources().getColor(R.color.btn_color));
        btnTip25.setBackgroundColor(getResources().getColor(R.color.btn_color));
        btnTip30.setBackgroundColor(getResources().getColor(R.color.btn_color));
    }

    private void initComponents() {
        tietBill = findViewById(R.id.tiet_bill);
        tietNrPers = findViewById(R.id.tiet_nr_people);

        btnTip5 = findViewById(R.id.btn_tip_5);
        btnTip10 = findViewById(R.id.btn_tip_10);
        btnTip15 = findViewById(R.id.btn_tip_15);
        btnTip20 = findViewById(R.id.btn_tip_20);
        btnTip25 = findViewById(R.id.btn_tip_25);
        btnTip30 = findViewById(R.id.btn_tip_30);

        btnCalculate = findViewById(R.id.btn_calculate);

        tvTipAmount = findViewById(R.id.tv_tip_amount);
        tvTipAmountPers = findViewById(R.id.tv_tip_amount_person);
        tvTotal = findViewById(R.id.tv_total);
        tvTotalPers = findViewById(R.id.tv_tip_total_person);
    }
}