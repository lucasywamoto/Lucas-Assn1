package com.lucas.assn1;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewEvaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight_325);
        editTextHeight = findViewById(R.id.editTextHeight_325);
        buttonCalculate = findViewById(R.id.buttonCalculate_325);
        textViewResult = findViewById(R.id.textViewResult_325);
        textViewEvaluation = findViewById(R.id.textViewEvaluation_325);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from inputs
                String weightStr = editTextWeight.getText().toString();
                String heightStr = editTextHeight.getText().toString();

                //check if user has entered both fields
                if (!weightStr.isEmpty() && !heightStr.isEmpty()) {

                    try {
                        //convert strings to float
                        float weight = Float.parseFloat(weightStr);
                        float height = Float.parseFloat(heightStr);

                        //check if user inputs are between the range of weight and height
                        if (weight < 20 || weight > 300 || height < 0.5 || height > 2.5) {
                            textViewResult.setText("Please enter a valid weight and height.");
                            textViewResult.setVisibility(View.VISIBLE);
                        } else {
                            //calculate bmi
                            float bmi = weight / (height * height);

                            //show results
                            textViewResult.setText(String.format("Your BMI is %.2f", bmi));
                            textViewResult.setVisibility(View.VISIBLE);

                            String evaluation;
                            int backgroundColor;

                            //evaluate the bmi in underweight, normal, overweight or obesity and set background color accordingly
                            if (bmi <= 18.4) {
                                evaluation = "Underweight";
                                backgroundColor = Color.parseColor("#FFFACD"); // Yellow for underweight
                            } else if (bmi >= 18.5 && bmi <= 24.9) {
                                evaluation = "Normal ";
                                backgroundColor = Color.parseColor("#90EE90"); // Green for normal
                            } else if (bmi >= 25.0 && bmi <= 39.9) {
                                evaluation = "Overweight";
                                backgroundColor = Color.parseColor("#FFD580"); // Orange for overweight
                            } else {
                                evaluation = "Obesity";
                                backgroundColor = Color.parseColor("#FF7F7F"); // Red for obese
                            }

                            //create shape behind the evaluation output
                            GradientDrawable drawable = new GradientDrawable();
                            drawable.setShape(GradientDrawable.RECTANGLE);
                            drawable.setCornerRadius(20f);
                            drawable.setColor(backgroundColor);

                            //show results
                            textViewEvaluation.setText(evaluation);
                            textViewEvaluation.setBackground(drawable);
                            textViewEvaluation.setVisibility(View.VISIBLE);
                        }
                    } catch (NumberFormatException e) {
                        //if input is not a number show error
                        textViewResult.setText("Input error, try again.");
                        textViewResult.setVisibility(View.VISIBLE);
                    }
                } else {
                    //if any of the inputs is empty show error
                    textViewResult.setText("Weight or height is empty.");
                    textViewResult.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
