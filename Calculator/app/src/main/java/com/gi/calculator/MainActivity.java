package com.gi.calculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bracket, addition, substract, multiply, modulus, division, clear, delete, dot, equal;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        b1 = findViewById(R.id.oneBtn);
        b2 = findViewById(R.id.twoBtn);
        b3 = findViewById(R.id.threeBtn);
        b4 = findViewById(R.id.fourBtn);
        b5 = findViewById(R.id.fiveBtn);
        b6 = findViewById(R.id.sixBtn);
        b7 = findViewById(R.id.sevenBtn);
        b8 = findViewById(R.id.eightBtn);
        b9 = findViewById(R.id.nineBtn);
        b0 = findViewById(R.id.zeroBtn);
        bracket = findViewById(R.id.bracketBtn);
        addition = findViewById(R.id.plus);
        substract = findViewById(R.id.substractionBtn);
        multiply = findViewById(R.id.multiplication);
        modulus = findViewById(R.id.modulusBtn);
        division = findViewById(R.id.division);
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.deleteBtn);
        dot = findViewById(R.id.dotBtn);
        equal = findViewById(R.id.equalBtn);

        input.setShowSoftInputOnFocus(false);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.msg).equals(input.getText().toString())) {
                    input.setText("");
                }
            }
        });
    }

    public void updateText(String strToAdd) {
        String oldstring = input.getText().toString();
        int cursorPosition = input.getSelectionStart();
        String leftStr = oldstring.substring(0, cursorPosition);
        String rightStr = oldstring.substring(cursorPosition);
        if (getString(R.string.msg).equals(input.getText().toString())) {
            input.setText("");
            input.setSelection(cursorPosition + 1);
        } else {
            input.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            input.setSelection(cursorPosition + 1);
        }
    }

    public void oneBtn(View v) {
        updateText("1");
    }

    public void twoBtn(View v) {
        updateText("2");
    }

    public void threeBtn(View v) {
        updateText("3");
    }

    public void fourBtn(View v) {
        updateText("4");
    }

    public void fiveBtn(View v) {
        updateText("5");
    }

    public void sixBtn(View v) {
        updateText("6");
    }

    public void sevenBtn(View v) {
        updateText("7");
    }

    public void eightBtn(View v) {
        updateText("8");
    }

    public void nineBtn(View v) {
        updateText("9");
    }

    public void zeroBtn(View v) {
        updateText("0");
    }

    public void addition(View v) {
        updateText("+");
    }

    public void multiplyBtn(View v) {
        updateText("*");
    }

    public void subBtn(View v) {
        updateText("-");
    }

    public void bracketBtn(View v) {
        int cursorPos = input.getSelectionStart();
        int openBracket = 0;
        int closeBracket = 0;
        int texLength = input.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (input.getText().toString().substring(i, i + 1).equals("(")) {
                openBracket += 1;
            }
            if (input.getText().toString().substring(i, i + 1).equals(")")) {
                closeBracket += 1;
            }

        }
        if (openBracket == closeBracket || input.getText().toString().substring(texLength - 1, texLength).equals("(")) {
            updateText("(");

        } else if (closeBracket < openBracket && !input.getText().toString().substring(texLength - 1, texLength).equals("(")) {
            updateText(")");
        }
        input.setSelection(cursorPos + 1);
    }

    public void dotBtn(View v) {
        updateText(".");
    }

    public void divisionBtn(View v) {
        updateText("/");
    }

    public void clearBtn(View v) {
        input.setText("");
    }

    public void delBtn(View v) {
        int position = input.getSelectionStart();
        int textLength = input.getText().length();

        if (position != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) input.getText();
            selection.replace(position - 1, position, "");
            input.setText(selection);
            input.setSelection(position - 1);
        }
    }

    public void equalBtn(View v) {
        String userExp = input.getText().toString();
        userExp = userExp.replaceAll("/","/");
        userExp = userExp.replaceAll("-","-");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        input.setText(result);
        input.setSelection(result.length());
        updateText("=");
    }

    public void modulusBtn(View v) {
        updateText("%");
    }
}