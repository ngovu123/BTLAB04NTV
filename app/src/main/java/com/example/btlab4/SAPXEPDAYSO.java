package com.example.btlab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class SAPXEPDAYSO extends AppCompatActivity {

    private EditText inputNumbersEditText;
    private RadioGroup sortingOptions;
    private EditText sortedNumbersEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sapxepdayso);

        inputNumbersEditText = findViewById(R.id.edtInputNumbers);
        sortingOptions = findViewById(R.id.radioGroup);
        sortedNumbersEditText = findViewById(R.id.edtSortedNumbers);
        resultTextView = findViewById(R.id.tvResult);

        Button sortButton = findViewById(R.id.btnSort);
        Button inputButton = findViewById(R.id.btnInput);
        Button clearButton = findViewById(R.id.btnClear);
        Button closeButton = findViewById(R.id.btnClose);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numbersStr = inputNumbersEditText.getText().toString();

                if (numbersStr.isEmpty()) {
                    resultTextView.setText("Dãy số không hợp lệ.");
                    sortedNumbersEditText.setText("");
                    return;
                }

                int[] numbers = parseNumbers(numbersStr);


                int selectedId = sortingOptions.getCheckedRadioButtonId();
                String sortingAlgorithm = "";
                if (selectedId == R.id.radioSelectionSort) {
                    sortingAlgorithm = "Selection Sort";
                    selectionSort(numbers);
                } else if (selectedId == R.id.radioBubbleSort) {
                    sortingAlgorithm = "Bubble Sort";
                    bubbleSort(numbers);
                } else if (selectedId == R.id.radioInterchangeSort) {
                    sortingAlgorithm = "Interchange Sort";
                    interchangeSort(numbers);
                }

                String sortedNumbersStr = Arrays.toString(numbers);
                resultTextView.setText("Dãy số sau khi sắp xếp theo " + sortingAlgorithm + ":");
                sortedNumbersEditText.setText(sortedNumbersStr);
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inputIntent = new Intent(SAPXEPDAYSO.this, InputActivity.class);
                startActivityForResult(inputIntent, 1); // Request code 1
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputNumbersEditText.setText("");
                sortedNumbersEditText.setText("");
                resultTextView.setText("");
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {

                String enteredNumbers = data.getStringExtra("enteredNumbers");
                inputNumbersEditText.setText(enteredNumbers);
            }
        }
    }

    private int[] parseNumbers(String numbersStr) {
        String[] parts = numbersStr.split(",");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }
    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void interchangeSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
