package com.example.temperature_convertor;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText tempInput;
    private AutoCompleteTextView fromUnit, toUnit;
    private MaterialButton convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempInput = findViewById(R.id.tempInput);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        setupDropdowns();

        convertButton.setOnClickListener(v -> performConversion());
    }

    private void setupDropdowns() {
        String[] units = {getString(R.string.celsius), getString(R.string.fahrenheit), getString(R.string.kelvin)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, units);
        
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);
        
        // Set default values
        fromUnit.setText(units[0], false);
        toUnit.setText(units[1], false);
    }

    private void performConversion() {
        String inputStr = tempInput.getText().toString();
        if (inputStr.isEmpty()) {
            tempInput.setError("Please enter a value");
            return;
        }

        double inputVal = Double.parseDouble(inputStr);
        String from = fromUnit.getText().toString();
        String to = toUnit.getText().toString();
        double result = 0;

        if (from.equals(to)) {
            result = inputVal;
        } else if (from.equals(getString(R.string.celsius))) {
            if (to.equals(getString(R.string.fahrenheit))) {
                result = (inputVal * 9 / 5) + 32;
            } else if (to.equals(getString(R.string.kelvin))) {
                result = inputVal + 273.15;
            }
        } else if (from.equals(getString(R.string.fahrenheit))) {
            if (to.equals(getString(R.string.celsius))) {
                result = (inputVal - 32) * 5 / 9;
            } else if (to.equals(getString(R.string.kelvin))) {
                result = (inputVal - 32) * 5 / 9 + 273.15;
            }
        } else if (from.equals(getString(R.string.kelvin))) {
            if (to.equals(getString(R.string.celsius))) {
                result = inputVal - 273.15;
            } else if (to.equals(getString(R.string.fahrenheit))) {
                result = (inputVal - 273.15) * 9 / 5 + 32;
            }
        }

        resultText.setText(String.format(Locale.getDefault(), "%.2f %s", result, to));
    }
}
