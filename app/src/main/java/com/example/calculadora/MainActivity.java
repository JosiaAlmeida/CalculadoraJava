package com.example.calculadora;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText textResult;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.textResult);
        resultText = findViewById(R.id.resultText);
    }

    public void Result(View view){
        double calcular = calc(textResult.getText().toString());
        resultText.setText(String.valueOf(calcular));
    }

    public void cleanInputs(View view){
        textResult.setText("0");
        resultText.setText("0");
    }

    public double calc(String val)
    {
        try {
            char sinal;
            if(val.contains("+"))
                sinal = '+';
            else if(val.contains("-"))
                sinal = '-';
            else if(val.contains("*"))
                sinal = '*';
            else if(val.contains("/"))
                sinal = '/';
            else return Double.parseDouble(val);
            String[] splitValue = val.split(String.valueOf("\\"+sinal));
            List<Double> valueList = new ArrayList<Double>();
            for(int i=0; i< splitValue.length; i++){
                if(!splitValue[i].isEmpty()){
                    valueList.add(calc(splitValue[i]));
                }
                else {
                    valueList.add(calc(splitValue[i+1]));
                }
            }
            double value = valueList.get(0);
            valueList.remove(new Double(value));
            for(double result: valueList){
                    value = ope(value, result, sinal);
            }
            return value;
        }catch (Error err){
            throw err;
        }
    }
    public double ope(Double v1, double v2, char op)
    {
        switch (op)
        {
            case '+': return v1 + v2;
            case '-': return v1 - v2;
            case '*': return v1 * v2;
            default: return v1 / v2;
        }

    }
}