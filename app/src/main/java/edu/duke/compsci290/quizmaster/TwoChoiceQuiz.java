package edu.duke.compsci290.quizmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoChoiceQuiz extends AppCompatActivity {

    private Button myFirstButton;
    private Button mySecondButton;
    private static int ourButtonCount;
    private QuizGenerator myQuiz;
    private TextView myQuestionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_choice_quiz);
        myQuiz = new QuizGenerator();
        myQuestionView = (TextView) this.findViewById(R.id.question_text);
        myFirstButton = (Button) this.findViewById(R.id.one_button);
        mySecondButton = (Button) this.findViewById(R.id.two_button);
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);

        // get question index to save question state
        outState.putInt("question_index", myQuiz.getMyQuestionIndex());
    }

    // This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
    // We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int i = savedInstanceState.getInt("question_index");
        myQuiz.setQuestionIndex(i); // call setter method to set saved question state
        setUpNextQuestion();
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
                    Intent i = new Intent(TwoChoiceQuiz.this, MainActivity.class);
                    startActivity(i);
                }
            })
                    // user wants to stop playing, kill app
                    .setNegativeButton("Stop Playing", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int p = android.os.Process.myPid(); // CAN'T FIGURE OUT HOW TO KILL APP
                            android.os.Process.killProcess(p);
                        }
                    });
            alertDialogBuilder.create().show();
        }
        else {
            myQuestionView.setText(q.getQuery());
            myFirstButton.setText(q.getMyCorrectAnswer());
            mySecondButton.setText(q.getMyWrongAnswer());
        }
    }

    public void leftClick(View button){
        ourButtonCount++;
        Toast.makeText(TwoChoiceQuiz.this,
                "left click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }
    public void rightClick(View button){
        ourButtonCount++;
        Toast.makeText(TwoChoiceQuiz.this,
                "right click, total = "+ourButtonCount,
                Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }
}
