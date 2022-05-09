package angelat.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView optionA,optionB,optionC,optionD;
    private TextView questionnumber,question,score;
    private TextView checkout1,checkout2;
    int currentIndex;
    int mscore=0;
    int qn=1;
    ProgressBar progressBar;
    int CurrentQuestion,CurrentOptionA,CurrentOptionB,CurrentOptionC,CurrentOptionD;





    private answerclass[]questionBank = new answerclass[]
            {
                     new answerclass(R.string.question_1,R.string.question1_A,R.string.question1_B,R.string.question1_C,R.string.question1_D,R.string.answer_1),
                     new answerclass(R.string.question_2,R.string.question_2A,R.string.question_2B,R.string.question_2C,R.string.question_2D,R.string.answer_2),
                     new answerclass(R.string.question_3,R.string.question_3A,R.string.question_3B,R.string.question_3C,R.string.question_3D,R.string.answer_3),
                    new answerclass(R.string.question_4,R.string.question_4A,R.string.question_4B,R.string.question_4C,R.string.question_4D,R.string.answer_4),
                    new answerclass(R.string.question_5,R.string.question_5A,R.string.question_5B,R.string.question_5C,R.string.question_5D,R.string.answer_5),
                    new answerclass(R.string.question_6,R.string.question_6A,R.string.question_6B,R.string.question_6C,R.string.question_6D,R.string.answer_6),
                    new answerclass(R.string.question_7,R.string.question_7A,R.string.question_7B,R.string.question_7C,R.string.question_7C,R.string.answer_7),
                    new answerclass(R.string.question_8,R.string.question_8A,R.string.question_8B,R.string.question_8C,R.string.question_8D,R.string.answer_8),

           };

    final int PROGRESS_BAR = (int)Math.ceil(100/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionA=(TextView)findViewById(R.id.optionA);
        optionB=(TextView) findViewById(R.id.optionB);
        optionC=(TextView) findViewById(R.id.optionC);
        optionD=(TextView) findViewById(R.id.optionD);


        question= findViewById(R.id.question);
        score = findViewById(R.id.score);
        questionnumber=findViewById(R.id.QuestionNumber);

        checkout1=findViewById(R.id.selectoption);
        checkout2=findViewById(R.id.correctAnswer);
        progressBar=findViewById(R.id.progressBar);

        CurrentQuestion = questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);

        CurrentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);

        CurrentOptionC=questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);

        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);


        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(CurrentOptionA);

                updateQuestion();


            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(CurrentOptionB);
                updateQuestion();

            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(CurrentOptionC);
                updateQuestion();

            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(CurrentOptionD);
                updateQuestion();

            }
        });





    }

    private void checkAnswer(int userSelection) {
        int correctanswer = questionBank[currentIndex].getAnswerid();

        checkout1.setText(userSelection);
        checkout2.setText(correctanswer);

        String m = checkout1.getText().toString().trim();
        String n = checkout2.getText().toString().trim();

        if(m.equals(n))
        {
            Toast.makeText(getApplicationContext(),"Right",Toast.LENGTH_SHORT).show();
            mscore=mscore+1;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
        }



    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion() {

        currentIndex = currentIndex + 1;

        if(currentIndex==questionBank.length)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You score: " + mscore + "points");
            alert.setPositiveButton("close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });


            alert.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mscore=0;
                    currentIndex = 0;
                    qn=1;
                    progressBar.setProgress(0);
                    score.setText("Score: " + mscore + " / " + questionBank.length);
                    questionnumber.setText(qn + " / " + questionBank.length + " Question");
                }
            });

            alert.show();
        } else {


            CurrentQuestion = questionBank[currentIndex].getQuestionid();
            question.setText(CurrentQuestion);
            CurrentOptionA = questionBank[currentIndex].getOptionA();
            optionA.setText(CurrentOptionA);

            CurrentOptionB = questionBank[currentIndex].getOptionB();
            optionB.setText(CurrentOptionB);

            CurrentOptionC = questionBank[currentIndex].getOptionC();
            optionC.setText(CurrentOptionC);

            CurrentOptionD = questionBank[currentIndex].getOptionD();
            optionD.setText(CurrentOptionD);

            qn = qn + 1;
            if (qn <= questionBank.length) {
                String qNumber = String.format("%s / %s Question", qn, questionBank.length);
                questionnumber.setText(qNumber);
            }

            String scoreText = String.format("Score: %s / %s", mscore, questionBank.length);
            score.setText(scoreText);
            progressBar.incrementProgressBy(PROGRESS_BAR);
        }
    }
}