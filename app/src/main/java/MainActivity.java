package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorapp.R;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solution_view,text_view;
    MaterialButton buttonC,buttonProduct,buttonDelete;
    MaterialButton button7,button8,button9,buttonDifference;
    MaterialButton button4,button5,button6,buttonPlus;
    MaterialButton button1,button2,button3,buttonDivision;
    MaterialButton buttonPercentage,buttonZero,buttonDot,buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution_view = findViewById(R.id.solutionView);
        text_view = findViewById(R.id.textView);

        assignedId(buttonC,R.id.btn_c);
        assignedId(buttonProduct,R.id.btn_product);
        assignedId(buttonDelete,R.id.btn_delete);
        assignedId(button7,R.id.btn_7);
        assignedId(button8,R.id.btn_8);
        assignedId(button9,R.id.btn_9);
        assignedId(buttonDifference,R.id.btn_difference);
        assignedId(button4,R.id.btn_4);
        assignedId(button5,R.id.btn_5);
        assignedId(button6,R.id.btn_6);
        assignedId(buttonPlus,R.id.btn_plus);
        assignedId(button1,R.id.btn_1);
        assignedId(button2,R.id.btn_2);
        assignedId(button3,R.id.btn_3);
        assignedId(buttonDivision,R.id.btn_division);
        assignedId(buttonPercentage,R.id.btn_percentage);
        assignedId(buttonZero,R.id.btn_zero);
        assignedId(buttonDot,R.id.btn_dot);
        assignedId(buttonEqual,R.id.btn_equal);
    }

    void assignedId(MaterialButton button,int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String calculation = solution_view.getText().toString();

        if (buttonText.equals("D")) {
            solution_view.setText("");
            text_view.setText("0");
            return;
        }


        if (buttonText.equals("=")) {
            solution_view.setText(text_view.getText());
            return;
        }

        //Clearing the last character
        if (buttonText.equals("C")) {
            calculation = calculation.substring(0, calculation.length() - 1);
        } else {
            calculation = calculation + buttonText;
        }

        //Concatenating the buttons by clicking them
        solution_view.setText(calculation);

        String finalResult = getResult(calculation);
        if(!finalResult.equals("Error")){
            text_view.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}
