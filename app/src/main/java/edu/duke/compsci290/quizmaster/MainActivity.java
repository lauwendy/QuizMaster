package edu.duke.compsci290.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button twoChoiceButton;
    private Button fourChoiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoChoiceButton = (Button) this.findViewById(R.id.two_choice_button);
        fourChoiceButton = (Button) this.findViewById(R.id.four_choice_button);

    }

    // the two methods below define what happens when you click either button

    // when you select FOUR choice quiz, go to two choice quiz page
    public void setTwoChoiceClick(View button){
        // how do you get from one activity to another, you use intents!
        // instantiate a new intent, pass in where you currently are, and then where you want to go
        Intent i = new Intent(MainActivity.this, TwoChoiceQuiz.class);
        startActivity(i);
    }

    // when you select FOUR choice quiz, go to four choice quiz page
    public void setFourChoiceClick(View button){
        Intent i = new Intent(MainActivity.this, FourChoiceQuiz.class);
        startActivity(i);
    }

}
