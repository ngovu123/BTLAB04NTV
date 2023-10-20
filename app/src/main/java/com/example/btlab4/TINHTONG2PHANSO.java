package com.example.btlab4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TINHTONG2PHANSO extends AppCompatActivity {

    EditText edtNum1, edtDen1, edtNum2, edtDen2;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtong2_phanso);

        edtNum1 = findViewById(R.id.etNum1);
        edtDen1 = findViewById(R.id.etDen1);
        edtNum2 = findViewById(R.id.etNum2);
        edtDen2 = findViewById(R.id.etDen2);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvKetQua);

        btnCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(edtNum1.getText().toString());
                int den1 = Integer.parseInt(edtDen1.getText().toString());
                int num2 = Integer.parseInt(edtNum2.getText().toString());
                int den2 = Integer.parseInt(edtDen2.getText().toString());


                int commonDenominator = den1 * den2;
                int numerator = (num1 * den2) + (num2 * den1);


                int gcd = gcd(numerator, commonDenominator);
                numerator /= gcd;
                commonDenominator /= gcd;

                tvResult.setText("Kết quả: " + numerator + "/" + commonDenominator);
            }
        });
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
