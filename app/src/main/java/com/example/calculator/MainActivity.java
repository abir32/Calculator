package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_View, solution_View;
    MaterialButton buttonAC,buttonC,buttonMod;
    MaterialButton buttonDiv,buttonX,buttonMinus,buttonPlus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_View = findViewById(R.id.result_view);
        solution_View = findViewById(R.id.solution_view);

        assignID(buttonAC,R.id.button_ac);
        assignID(buttonC,R.id.button_c);
        assignID(buttonMod,R.id.button_mod);
        assignID(buttonDiv,R.id.button_div);
        assignID(buttonX,R.id.button_X);
        assignID(buttonMinus,R.id.button_minus);
        assignID(buttonPlus,R.id.button_plus);
        assignID(buttonEqual,R.id.button_equal);
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(buttonDot,R.id.button_dot);


    }

    void assignID(MaterialButton btn, int id){

        btn= findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_View.getText().toString();

        if (buttonText.equals("AC")){
            solution_View.setText("");
            result_View.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solution_View.setText(result_View.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }


        solution_View.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            result_View.setText(finalResult);
        }


    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript", 1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }


}