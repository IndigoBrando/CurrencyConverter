package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText amountInput;
    private Spinner fromCurrency;
    private Spinner toCurrency;
    private Button convertButton;
    private TextView resultText;

    private final String[] currencies = {"USD", "EUR", "INR", "JPY", "GBP", "PHP"};
    private final double[][] exchangeRates = {
            {1.0, 0.85, 74.50, 110.0, 0.73, 56.0},  // USD to other exc rates
            {1.18, 1.0, 87.65, 129.4, 0.85, 63.5},  // EUR to other exc rates
            {0.013, 0.011, 1.0, 1.48, 0.0098, 0.75},  // INR to other ex rates
            {0.0091, 0.0077, 0.67, 1.0, 0.0067, 0.39},  // JPY to other exc rates
            {1.37, 1.17, 102.7, 149.3, 1.0, 68.0},  // GBP to other exc rates
            {0.018, 0.016, 1.33, 2.56, 0.014, 1.0}   // PHP to other exc rates
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        amountInput = findViewById(R.id.amountInput);
        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        // Set Convert button listener
        convertButton.setOnClickListener(v -> convertCurrency());
    }

    private void convertCurrency() {
        String amountStr = amountInput.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        int fromIndex = fromCurrency.getSelectedItemPosition();
        int toIndex = toCurrency.getSelectedItemPosition();

        double conversionRate = exchangeRates[fromIndex][toIndex];
        double convertedAmount = amount * conversionRate;

        String result = String.format("Converted Amount: %.2f %s", convertedAmount, currencies[toIndex]);
        resultText.setText(result);
    }
}
