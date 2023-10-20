package com.example.btlab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    private EditText inputNumbersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        inputNumbersEditText = findViewById(R.id.inputNumbersEditText);
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredNumbers = inputNumbersEditText.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("enteredNumbers", enteredNumbers);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
