package com.example.converts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner unitSpinner,unitSpinner1;
    private TextView resultTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //calling view from XML
        inputValue = findViewById(R.id.inputValue1);
        unitSpinner = findViewById(R.id.unitSpinner1);
        unitSpinner1 = findViewById(R.id.unitSpinner2);
        Button convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);
        //Make adapter Arry
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, R.layout.dropdown);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set adapter in UnitSpinner
        unitSpinner.setAdapter(adapter);
        unitSpinner1.setAdapter(adapter);
        //click button function
        convertButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //convert function
                convertUnits();

            }

        });
    }
    private void convertUnits() {

        // Get input value and selected unit from spinner

        String input = inputValue.getText().toString();

        //check input is empty or not
        if (input.isEmpty()) {

            resultTextView.setText("Please enter a value");

            return;
        }

        double value = Double.parseDouble(input);
        //get selected item text
        String selectedUnit = unitSpinner.getSelectedItem().toString();
        String selectedUnit1 = unitSpinner1.getSelectedItem().toString();



        double result = 0;
// Perform conversion based on selected unit
        if (selectedUnit.equals("Millimeter"))
        {
            if(selectedUnit1.equals("Millimeter")){

                resultTextView.setText(value + " mm = " +value + " mm");
            } else if (selectedUnit1.equals("Centimeters")) {
                result= MilliToCm(value,"");
                resultTextView.setText(value + " mm = " + result + " cm");
            }
            else if (selectedUnit1.equals("Decimeter")) {
                result= MilliToCm(value,"")/10;

                resultTextView.setText(value + " mm = " + result + " dm");
            }
            else if (selectedUnit1.equals("Meter")) {
                result= MilliToCm(value,"")/100;

                resultTextView.setText(value + " mm = " + result + " m");
            }

            else if (selectedUnit1.equals("Kilometer")) {
                result= MilliToCm(value,"")/100000;

                resultTextView.setText(value + " mm = " + result + " km");
            }

        }
        else if (selectedUnit.equals("Centimeters"))
        {
            if(selectedUnit1.equals("Millimeter")){
                result= MilliToCm(value,"normal");

                resultTextView.setText(value + " cm = " + result + " mm");
            } else if (selectedUnit1.equals("Centimeters")) {

                resultTextView.setText(value + " cm = " + value + " cm");
            }
            else if (selectedUnit1.equals("Decimeter")) {
                result= value/10;
                resultTextView.setText(value + " cm = " + result + " dm");
            }
            else if (selectedUnit1.equals("Meter")) {
                result= value/100;

                resultTextView.setText(value + " cm = " + result + " m");
            }

            else if (selectedUnit1.equals("Kilometer")) {
                result= value/100000;

                resultTextView.setText(value + " cm = " + result + " km");
            }

        }
        else if (selectedUnit.equals("Decimeter"))
        {
            if(selectedUnit1.equals("Millimeter")){
                result=value*10;
                result= MilliToCm(result,"normal");

                resultTextView.setText(value + " dm = " + result + " mm");
            } else if (selectedUnit1.equals("Centimeters")) {
                result=value*10;
                resultTextView.setText(value + " dm = " + result + " cm");
            }
            else if (selectedUnit1.equals("Decimeter")) {

                resultTextView.setText(value + " dm = " +value + " dm");
            }
            else if (selectedUnit1.equals("Meter")) {
                result= value/10;

                resultTextView.setText(value + " dm = " + result + " m");
            }

            else if (selectedUnit1.equals("Kilometer")) {
                result= value/10000;

                resultTextView.setText(value + " dm = " + result + " km");
            }

        }
        else if (selectedUnit.equals("Meter"))
        {
            if(selectedUnit1.equals("Millimeter")){
                result=value*100;
                result= MilliToCm(result,"normal");

                resultTextView.setText(value + " dm = " + result + " mm");
            }
            else if (selectedUnit1.equals("Centimeters")) {
                result=value*100;
                resultTextView.setText(value + " m = " + result + " cm");
            }
            else if (selectedUnit1.equals("Decimeter")) {
                result= value*10;
                resultTextView.setText(value + " m = " + result + " dm");
            }
            else if (selectedUnit1.equals("Meter")) {

                resultTextView.setText(value + " m = " + value + " m");
            }

            else if (selectedUnit1.equals("Kilometer")) {
                result= value/1000;

                resultTextView.setText(value + " m = " + result + " km");
            }

        }
        else if (selectedUnit.equals("Kilometer"))
        {
            if(selectedUnit1.equals("Millimeter")){
                result=value*100000;
                result= MilliToCm(result,"normal");

                resultTextView.setText(value + " km = " + result + " mm");
            } else if (selectedUnit1.equals("Centimeters")) {
                result=value*100000;
                resultTextView.setText(value + " km = " + result + " cm");
            }
            else if (selectedUnit1.equals("Decimeter")) {
                result= value*10000;
                resultTextView.setText(value + " km = " + result + " dm");
            }
            else if (selectedUnit1.equals("Meter")) {
                result= value*1000;
                resultTextView.setText(value + " km = " + result + " m");
            }

            else if (selectedUnit1.equals("Kilometer")) {
                resultTextView.setText(value + " km = " + value + " km");
            }

        }

    }


    private double MilliToCm(double first ,String Type){
        if(Type.equals("normal")){
            return (first*10);
        }

        else {
            return (first/10);
        }

    }



}