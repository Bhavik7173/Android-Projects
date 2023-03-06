package com.gi.millioners.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.millioners.R;
import com.gi.millioners.model.QuestionPojo;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView question, heading, qCount, op_c, op_d, textError, price;
    Button next, submit;
    RadioButton a, b, c, d;
    LinearLayout mainView;
    String titleId, title, totalQuestion;
    String answerValue = "";
    ArrayList<QuestionPojo> quizModelArrayList;
    int count = 0;
    boolean flag = true;
    int cnt = 0;
    int nonCnt = 0;
    int winning_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design1);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        question = findViewById(R.id.question);
        mainView = findViewById(R.id.mainView);
        textError = findViewById(R.id.textError);
        heading = findViewById(R.id.heading);
        qCount = findViewById(R.id.qCount);
        next = findViewById(R.id.next);
        submit = findViewById(R.id.submit);

        op_c = findViewById(R.id.c_op);
        op_d = findViewById(R.id.d_op);
        quizModelArrayList = new ArrayList();
        title = "Quiz";
        price = findViewById(R.id.price);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioValue(true, false, false, false, "A");

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioValue(false, true, false, false, "B");
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioValue(false, false, true, false, "C");
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRadioValue(false, false, false, true, "D");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                setRadioValue(false, false, false, false, "");
                setValues(count, quizModelArrayList);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValues(count, quizModelArrayList);
                if (count == quizModelArrayList.size()) {
                } else {
                    checkAnswer();
                }
            }
        });
        getQuestionData(quizModelArrayList);
        setValues(0,quizModelArrayList);
    }

    void setRadioValue(boolean value1, boolean value2, boolean value3, boolean value4, String opValue) {
        a.setChecked(value1);
        b.setChecked(value2);
        c.setChecked(value3);
        d.setChecked(value4);
        answerValue = opValue;
        if (opValue != "") {
            quizModelArrayList.get(count).setUser_answer(opValue);
        }
    }

    private void setValues(int count, List<QuestionPojo> pojo) {
        op_c.setVisibility(View.VISIBLE);
        op_d.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        int QNo = count + 1;
        qCount.setText(QNo + "/" + pojo.size());
        price.setText(pojo.get(count).getPrice());
        question.setText("Q" + QNo + ") " + pojo.get(count).getQ_question());
        a.setText(pojo.get(count).getOptionA());
        b.setText(pojo.get(count).getOptionB());

//        c.setText(pojo.get(count).getOptionC());
//        d.setText(pojo.get(count).getOptionD());
        if (pojo.get(count).getOptionC() == null) {
            c.setVisibility(View.INVISIBLE);
            op_c.setVisibility(View.INVISIBLE);
        } else {
            c.setText(pojo.get(count).getOptionC());
        }
        if (pojo.get(count).getOptionD() == null) {
            op_d.setVisibility(View.INVISIBLE);
            d.setVisibility(View.INVISIBLE);
        } else {
            d.setText(pojo.get(count).getOptionD());
        }


        if (count == quizModelArrayList.size() - 1) {
            submit.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);
        } else {
            submit.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public void checkAnswer() {
        int cnt = 0;
        int nonCnt = 0;
        int winning_price = 0;
        for (int i = 0; i < quizModelArrayList.size(); i++) {
            if (quizModelArrayList.get(i).getUser_answer().equals(quizModelArrayList.get(i).getCorrect_answer())) {
                cnt++;
                winning_price = winning_price + Integer.parseInt(quizModelArrayList.get(i).getPrice());
            } else if (quizModelArrayList.get(i).getUser_answer().equals("Z")) {
                nonCnt++;
                winning_price = winning_price - Integer.parseInt(quizModelArrayList.get(i).getPrice());
            }
        }

        Log.d("gilog", cnt + "");
        Log.d("gilog", title + "");
        Log.d("gilog", quizModelArrayList.size() + "");
        Log.d("gilog", nonCnt + "");
        Log.d("gilog", winning_price + "");

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("count", String.valueOf(cnt));
        intent.putExtra("title", title);
        intent.putExtra("totalQuestion", "" + quizModelArrayList.size());
        intent.putExtra("nonAttempt", String.valueOf(nonCnt));
        intent.putExtra("winning_price", String.valueOf(winning_price));

        intent.putParcelableArrayListExtra("list", quizModelArrayList);
        startActivity(intent);


    }

    public void getQuestionData(ArrayList<QuestionPojo> quizModelArrayList) {
        quizModelArrayList.add(new QuestionPojo("1", "Which one is the smallest ocean in the world?", "Indian", "Pacific", "Atlantic", "Arctic", "D", "100"));
        quizModelArrayList.add(new QuestionPojo("2", "Which country gifted the 'Statue of Liberty' to USA in 1886?", "France", "Canada", "Brazil", "England", "A", "100"));
        quizModelArrayList.add(new QuestionPojo("3", "Dead Sea is located between which two countries?", "Jordan And Sudan", "Jordan And Israel", "Turkey And UAE", "UAE And Egypt", "B", "100"));
        quizModelArrayList.add(new QuestionPojo("4", "Which country is known as the 'Land of Thanderbolts'?", "China", "Bhutan", "Mongolia", "Thailand", "B", "100"));
//        quizModelArrayList.add(new QuestionPojo("5", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("6", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("7", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("8", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("9", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("10", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("11", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("12", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("13", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("14", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));
//        quizModelArrayList.add(new QuestionPojo("15", "Which plateau is known as the 'Roof of the world'?", "Andes", "Himalaya", "Karakoram", "Pamir", "D", "100"));

    }

    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
