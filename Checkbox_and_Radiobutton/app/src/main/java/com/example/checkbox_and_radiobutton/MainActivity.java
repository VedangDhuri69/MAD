package com.example.checkbox_and_radiobutton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbReading, cbCoding, cbSports;
    private RadioGroup radioGroupGender;
    private Button btnSubmit;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        cbReading = findViewById(R.id.cbReading);
        cbCoding = findViewById(R.id.cbCoding);
        cbSports = findViewById(R.id.cbSports);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);

        btnSubmit.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder();
            
            // Handle CheckBoxes (Hobbies)
            result.append("Hobbies: ");
            List<String> hobbies = new ArrayList<>();
            if (cbReading.isChecked()) hobbies.add("Reading");
            if (cbCoding.isChecked()) hobbies.add("Coding");
            if (cbSports.isChecked()) hobbies.add("Sports");
            
            if (hobbies.isEmpty()) {
                result.append("None selected");
            } else {
                for (int i = 0; i < hobbies.size(); i++) {
                    result.append(hobbies.get(i));
                    if (i < hobbies.size() - 1) {
                        result.append(", ");
                    }
                }
            }

            result.append("\n\n");

            // Handle RadioGroup (Gender)
            int selectedId = radioGroupGender.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                result.append("Gender: ").append(selectedRadioButton.getText());
            } else {
                result.append("Gender: Not selected");
            }

            // Display result
            tvResult.setText(result.toString());
        });
    }
}