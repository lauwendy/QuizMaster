package edu.duke.compsci290.quizmaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FourChoiceQuiz extends AppCompatActivity {

    private Button myFirstButton;
    private Button mySecondButton;
    private Button myThirdButton;
    private Button myFourthButton;
    private static int ourButtonCount;
    private QuizGenerator myQuiz;
    private TextView myQuestionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_choice_quiz);
        myQuiz = new QuizGenerator();
        myQuestionView = (TextView) this.findViewById(R.id.question_text);
        myFirstButton = (Button) this.findViewById(R.id.first_button);
        mySecondButton = (Button) this.findViewById(R.id.second_button);
        myThirdButton = (Button) this.findViewById(R.id.third_button);
        myFourthButton = (Button) this.findViewById(R.id.fourth_button);
    }
    @Override
    protected void onStart(){
        super.onStart();
        setUpNextQuestion();
    }

    // create separate method to set up next question
    private void setUpNextQuestion() {
        QuizGenerator.Question q = myQuiz.getQuestion();
        // when there are no more questions left, show popup prompting next action
        if (q == null) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Quiz Finished!");
            alertDialogBuilder.setMessage("What do you want to do next?");
            // user wants a new quiz, set up intent to go back to page with quiz options
            alertDialogBuilder.setPositiveButton("New Quiz", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ourButtonCount = 0; // restart button counter
                    Intent i = new Intent(FourChoiceQuiz.this, MainActivity.class);
                    startActivity(i);
                }
            })
                    // user wants to stop playing, kill app
                    .setNegativeButton("Stop Playing", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0); // CAN'T FIGURE OUT HOW TO KILL APP
                        }
                    });
            alertDialogBuilder.create().show();
        }
        else {
            myQuestionView.setText(q.getQuery());
            myFirstButton.setText(q.getMyCorrectAnswer());
            mySecondButton.setText(q.getMyWrongAnswer());
            myThirdButton.setText(q.getMyThirdAnswer());
            myFourthButton.setText(q.getMyFourthAnswer());
        }
    }

    public void leftClick(View button){
        ourButtonCount++;
        Toast.makeText(FourChoiceQuiz.this,
                "left click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }

    public void rightClick(View button){
        ourButtonCount++;
        Toast.makeText(FourChoiceQuiz.this,
                "right click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }

    public void thirdClick(View button){
        ourButtonCount++;
        Toast.makeText(FourChoiceQuiz.this,
                "third click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }

    public void fourthClick(View button){
        ourButtonCount++;
        Toast.makeText(FourChoiceQuiz.this,
                "fourth click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();

    }
}
